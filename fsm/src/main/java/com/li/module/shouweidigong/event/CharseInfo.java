package com.li.module.shouweidigong.event;

import com.li.map.Point;
import com.li.module.shouweidigong.model.Guard;
import lombok.Getter;


/**
 * @author li-yuanwen
 * 追逐信息
 */
@Getter
public class CharseInfo {

    /** 追逐目标点 **/
    private Point targetPoint;

    /** 追逐人 **/
    private Guard owner;

    public CharseInfo(Point targetPoint, Guard owner) {
        this.targetPoint = targetPoint;
        this.owner = owner;
    }
}
