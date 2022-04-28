package com.li.battle.core.scene.impl;

import com.li.battle.core.scene.AbstractBattleScene;
import com.li.battle.core.scene.map.SceneMap;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 多人对战PVP场景
 *
 * @author li-yuanwen
 * @date 2021/10/16
 */
public class MultipleFightBattleScene extends AbstractBattleScene {


    public MultipleFightBattleScene(long sceneId, SceneMap sceneMap, ScheduledExecutorService executorService) {
        super(sceneId, sceneMap, executorService);
    }

    @Override
    public int getRoundPeriod() {
        return 50;
    }

    @Override
    protected void startRunningScheduler() {
        // 每50ms执行一次战斗了
        this.executorService.scheduleAtFixedRate(this::start, 0, getRoundPeriod(), TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean checkDestroy() {
        // 场景内无战斗单元,可销毁
        return this.fightUnits.isEmpty();
    }

    private void start() {
        try {
            // 更新回合数
            updateRound(1);
            // 执行玩家操作
            executePlayerOperates();
            // 开始执行子弹逻辑
            executeProjectiles();
            // 开始执行buff逻辑
            executeBuffs();
            // 执行技能
            executeSkills();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
