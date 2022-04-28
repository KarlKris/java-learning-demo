package com.li.battle.core.scene;

import com.li.battle.buff.core.Buff;
import com.li.battle.core.scene.map.SceneMap;
import com.li.battle.core.task.PlayerOperateTask;
import com.li.battle.core.unit.FightUnit;
import com.li.battle.projectile.core.Projectile;
import com.li.battle.skill.executor.BattleSkillExecutor;
import com.li.battle.skill.model.BattleSkillCtx;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 抽象战斗场景基类
 * @author li-yuanwen
 */
public abstract class AbstractBattleScene implements BattleScene {

    /** 场景唯一id **/
    protected final long sceneId;

    /** 场景地图 **/
    protected final SceneMap sceneMap;

    /** 战斗单元容器 **/
    protected final Map<Long, FightUnit> fightUnits;

    /** Buff容器 **/
    protected final List<Buff> buffs;

    /** 子弹容器 **/
    protected final List<Projectile> projectiles;

    /** 技能容器 **/
    protected final List<BattleSkillCtx> skills;

    /** 单线程池(定时执行战斗逻辑) **/
    protected final ScheduledExecutorService executorService;

    /** 玩家操作队列 **/
    protected final Queue<PlayerOperateTask> queue = new ConcurrentLinkedQueue<>();

    /** 场景运行状态 **/
    protected volatile boolean running;

    /** 场景当前回合数 **/
    private long round;

    /** 技能执行器 **/
    private final BattleSkillExecutor battleSkillExecutor;

    public AbstractBattleScene(long sceneId, SceneMap sceneMap, ScheduledExecutorService executorService) {
        this.sceneId = sceneId;
        this.sceneMap = sceneMap;
        this.fightUnits = new HashMap<>();
        this.buffs = new LinkedList<>();
        this.projectiles = new LinkedList<>();
        this.skills = new LinkedList<>();
        this.executorService = executorService;
        this.battleSkillExecutor = (skillCtx, scene) -> false;
    }

    @Override
    public long getSceneId() {
        return this.sceneId;
    }

    @Override
    public long getSceneRound() {
        return round;
    }

    @Override
    public boolean enterScene(FightUnit unit) {
        boolean success = fightUnits.putIfAbsent(unit.getId(), unit) == null;
        if (success) {
            trySceneRunning();
        }
        return success;
    }

    @Override
    public void leaveScene(long unitId) {
        this.fightUnits.remove(unitId);
    }

    /** 尝试运行场景逻辑 **/
    protected void trySceneRunning() {
        if (!running) {
            return;
        }

        // 修改状态
        updateRunningState(true);
        // 开始运行场景逻辑定时器
        startRunningScheduler();

    }

    /** 运行场景逻辑，修改场景运行状态 **/
    protected void updateRunningState(boolean running) {
        this.running = running;
    }

    /** 开始运行场景逻辑 **/
    protected abstract void startRunningScheduler();

    /** 开始运行Buff逻辑 **/
    protected void executeBuffs() {
        long round = getSceneRound();
        Iterator<Buff> iterator = this.buffs.iterator();
        if (iterator.hasNext()) {
            Buff buff = iterator.next();
            // buff已失效,执行逻辑
            if (buff.expire(round)) {
                // 执行移除容器前逻辑
                buff.onBuffRemove();
                // 移除容器
                iterator.remove();
                // 执行移除容器后逻辑
                buff.onBuffDestroy();
            } else if (buff.getNextRound() <= round){
                buff.onIntervalThink();
            }
        }
    }

    /** 执行子弹逻辑 **/
    protected void executeProjectiles() {
        Iterator<Projectile> iterator = this.projectiles.iterator();
        if (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            // 更新子弹位置
            projectile.updatePosition();
            // 检查子弹是否命中目标
            if (projectile.checkProjectileHit()) {
                // todo 执行命中逻辑
            }

            // 检查子弹是否结束
            if (projectile.checkFinish()) {
                // 移除子弹容器
                iterator.remove();
            }
        }
    }

    /** 执行玩家操作 **/
    protected void executePlayerOperates() {
        int size = this.queue.size();
        for (int i = 0; i < size; i++) {
            try {
                PlayerOperateTask task = this.queue.poll();
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** 执行技能操作 **/
    protected void executeSkills() {
        Iterator<BattleSkillCtx> iterator = skills.iterator();
        while (iterator.hasNext()) {
            BattleSkillCtx skillCtx = iterator.next();
            if (skillCtx.getNextRound() > round) {
                continue;
            }
            if (skillCtx.getExpireRound() >= round
                    || battleSkillExecutor.execute(skillCtx, this)) {
                iterator.remove();
            }
        }
    }

    /** 更新回合数 **/
    protected void updateRound(int add) {
        this.round += add;
    }


}
