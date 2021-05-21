package com.li.module.shouweidigong.event;

import com.li.map.Point;
import com.li.module.shouweidigong.model.Thieves;
import lombok.Getter;

import java.util.List;

/**
 * @author li-yuanwen
 * 逃跑信息
 */
@Getter
public class EscapeInfo {

    /** 逃跑者 **/
    private Thieves owner;

    /** 搜寻路线 **/
    private List<Point> routes;

    /** 搜寻进度 **/
    private int index;

    public EscapeInfo(List<Point> routes, int index, Thieves owner) {
        this.owner = owner;
        this.routes = routes;
        this.index = index;
    }

}
