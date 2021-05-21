package com.li.module.shouweidigong.event;

import com.li.map.Point;
import com.li.module.shouweidigong.model.Thieves;
import lombok.Getter;

import java.util.List;

/**
 * @author li-yuanwen
 * 搜寻信息
 */
@Getter
public class SearchInfo {

    /** 搜寻路线 **/
    private List<Point> routes;

    /** 盗贼 **/
    private Thieves owner;

    /** 当前搜寻下标 **/
    private int index;

    public SearchInfo(List<Point> routes, int index, Thieves owner) {
        this.routes = routes;
        this.index = index;
        this.owner = owner;
    }
}
