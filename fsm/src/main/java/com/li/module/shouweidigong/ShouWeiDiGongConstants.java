package com.li.module.shouweidigong;

/**
 * @author li-yuanwen
 * 守卫地宫游戏规则常量
 */
public interface ShouWeiDiGongConstants {

    //速度(每一回合走多少格子)

    /** 普通格子标识 **/
    byte COMMON_ID = 0;

    // 守卫规则

    /** 守卫巡逻速度 **/
    int GUARD_PATROL_SPEED = 2;

    /** 守卫追逐速度 **/
    int GUARD_CHARSE_SPEED = 3;

    /** 守卫范围 **/
    int GUARD_RANGE = 8;

    /** 守卫视野范围 **/
    int GUARD_VISION_RANGE = 10;

    /** 守卫标识 **/
    byte GUARD_ID = 1;

    // 盗贼规则

    /** 盗贼搜寻速度 **/
    int THIEVES_SEARCH_SPEED = 2;

    /** 盗贼逃跑速度 **/
    int THIEVES_ESCAPE_SPEED = 2;

    /** 盗贼视野范围 **/
    int THIEVES_VISION_RANGE = 10;

    /** 守卫标识 **/
    byte THIEVES_ID = 2;

}
