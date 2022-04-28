package com.li.scene;

import com.li.unit.Unit;

import java.util.Collection;

/**
 * 战斗场景接口
 * @author li-yuanwen
 * @date 2022/4/25
 */
public interface GameScene {


    /**
     * 返回场景唯一id
     * @return 场景唯一id
     */
    long getSceneId();


    /**
     * 获取场景内所有单元
     * @return 场景内所有单元
     */
    Collection<Unit> getUnits();


}
