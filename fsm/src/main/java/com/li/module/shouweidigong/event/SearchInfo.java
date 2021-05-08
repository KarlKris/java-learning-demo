package com.li.module.shouweidigong.event;

import com.li.map.Point;

import java.util.List;

/**
 * @author li-yuanwen
 * 搜寻信息
 */
public class SearchInfo {

    /** 搜寻路线 **/
    private List<Point> routes;

    /** 当前搜寻下标 **/
    private int index;

    public SearchInfo(List<Point> routes, int index) {
        this.routes = routes;
        this.index = index;
    }
}
