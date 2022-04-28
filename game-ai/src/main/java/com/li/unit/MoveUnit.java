package com.li.unit;

import com.li.common.IntVector2D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * 移动单位接口
 * @author li-yuanwen
 * @date 2022/4/25
 */
public interface MoveUnit extends Unit {


    /**
     * 获取可达到的最大速度
     * @return 可达到的最大速度
     */
    int getMaxSpeed();

    /**
     * 获取当前速度矢量
     * @return 当前速度矢量
     */
    IntVector2D getVelocity();

    /**
     * 获取当前速度 == getVelocity().getNorm()
     * @return 当前速度
     */
    int getSpeed();

    /**
     * 获取当前朝向
     * @return 当前朝向
     */
    IntVector2D getHeading();

    /**
     * 获取wander圈上的点(局部)
     * @return wander圈上的点
     */
    IntVector2D getLocalWander();

    /**
     * 更新wander圈上的点(局部)
     * @param localWander wander圈上的点
     */
    void updateLocalWander(IntVector2D localWander);

}
