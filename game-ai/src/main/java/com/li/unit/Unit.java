package com.li.unit;

import com.li.common.IntVector2D;

/**
 * 单位对外接口
 * @author li-yuanwen
 * @date 2022/4/25
 */
public interface Unit {

    /**
     * 获取单元唯一标识
     * @return 单元id
     */
    long getId();

    /**
     * 获取当前位置矢量
     * @return 当前位置矢量
     */
    IntVector2D getPosition();

    /**
     * 获取单位半径(即把单位看作半径为radius的圆柱体)
     * @return 单位半径
     */
    int getRadius();



}
