package com.li.battle.core.scene;

import com.li.battle.buff.core.Buff;
import com.li.battle.core.scene.map.SceneMap;
import com.li.battle.core.unit.FightUnit;
import com.li.battle.projectile.core.Projectile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /** 单线程池(定时执行战斗逻辑) **/
    protected final ScheduledExecutorService executorService;

    /** 场景运行状态 **/
    protected volatile boolean running;


    public AbstractBattleScene(long sceneId, SceneMap sceneMap, ScheduledExecutorService executorService) {
        this.sceneId = sceneId;
        this.sceneMap = sceneMap;
        this.fightUnits = new HashMap<>();
        this.buffs = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.executorService = executorService;
    }

    @Override
    public long getSceneId() {
        return this.sceneId;
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
        // 开始运行场景逻辑
        startRunning();

    }

    /** 运行场景逻辑，修改场景运行状态 **/
    protected void updateRunningState(boolean running) {
        this.running = running;
    }

    /** 开始运行场景逻辑 **/
    protected abstract void startRunning();



}
