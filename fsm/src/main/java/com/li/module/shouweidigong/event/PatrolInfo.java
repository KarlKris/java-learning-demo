package com.li.module.shouweidigong.event;

import com.li.map.Direction;
import com.li.module.shouweidigong.model.Guard;
import lombok.Getter;

/**
 * @author li-yuanwen
 * 巡逻信息
 */
@Getter
public class PatrolInfo {

    /** 方向 **/
    private Direction direction;

    /** 守卫人 **/
    private Guard owner;

    public PatrolInfo(Direction direction, Guard owner) {
        this.direction = direction;
        this.owner = owner;
    }
}
