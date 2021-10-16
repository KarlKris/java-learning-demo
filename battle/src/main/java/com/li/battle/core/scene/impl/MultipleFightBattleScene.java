package com.li.battle.core.scene.impl;

import com.li.battle.core.scene.AbstractBattleScene;
import com.li.battle.core.scene.map.SceneMap;

import java.util.concurrent.ScheduledExecutorService;

/**
 * 多人对战PVP场景
 * @author li-yuanwen
 * @date 2021/10/16
 */
public class MultipleFightBattleScene extends AbstractBattleScene {


    public MultipleFightBattleScene(long sceneId, SceneMap sceneMap, ScheduledExecutorService executorService) {
        super(sceneId, sceneMap, executorService);
    }


    @Override
    protected void startRunning() {

    }

    @Override
    public boolean checkDestroy() {
        // 场景内无战斗单元,可销毁
        return this.fightUnits.isEmpty();
    }
}
