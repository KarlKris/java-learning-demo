package com.li.module.shouweidigong;

import com.li.event.Event;
import com.li.event.EventConstant;
import com.li.map.Direction;
import com.li.map.Point;
import com.li.module.shouweidigong.event.PatrolInfo;
import com.li.module.shouweidigong.event.SearchInfo;
import com.li.module.shouweidigong.mathine.ShouWeiStateMathine;
import com.li.module.shouweidigong.model.Guard;
import com.li.module.shouweidigong.model.Thieves;
import com.li.state.StateConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author li-yuanwen
 * 守卫地宫游戏
 */
public class ShouWeiDiGong {

    /** 全局信息 **/
    private BlackBoard blackBoard;
    /** 地图 **/
    private ShouWeiDiGongMap map;

    /** AI **/
    private Map<Integer, ShouWeiStateMathine> ais;



    private void init() {
        blackBoard = new BlackBoard();
        map = new ShouWeiDiGongMap(100, 100);

        List<Event> initEvents = new ArrayList<>(6);
        int id = 1;

        int guardNum = 4;
        ais = new HashMap<>();
        for (int i = 0; i < guardNum / 2; i++) {
            Guard guard = new Guard(id++, StateConstant.PATROL, new Point((i + 1) * 30, 30), blackBoard, map);
            ais.put(guard.getId(), guard);
            Event<PatrolInfo> event = new Event<>(EventConstant.PATROL,
                    new PatrolInfo(Direction.randomDirection(), guard), guard.getId(), 1);

            initEvents.add(event);
        }
        for (int i = guardNum / 2; i < guardNum; i++) {
            Guard guard = new Guard(id++, StateConstant.PATROL, new Point((i + 1) * 30, 60), blackBoard, map);
            ais.put(guard.getId(), guard);
            Event<PatrolInfo> event = new Event<>(EventConstant.PATROL,
                    new PatrolInfo(Direction.randomDirection(), guard), guard.getId(), 1);

            initEvents.add(event);
        }

        Thieves thieves1 = new Thieves(id++, StateConstant.SEARCH, new Point(0, 0), blackBoard, map);
        Thieves thieves2 = new Thieves(id++, StateConstant.SEARCH, new Point(99, 99), blackBoard, map);

        ais.put(thieves1.getId(), thieves1);
        ais.put(thieves2.getId(), thieves2);

        Event<SearchInfo> event1 = new Event<>(EventConstant.SEARCH
                , new SearchInfo(map.pathFind(thieves1.getCurPoint()), 0), thieves1.getId(), 1);
        initEvents.add(event1);
        Event<SearchInfo> event2 = new Event<>(EventConstant.SEARCH
                , new SearchInfo(map.pathFind(thieves2.getCurPoint()), 0), thieves2.getId(), 1);
        initEvents.add(event2);

    }

    public void start() {
        init();
        while (!blackBoard.isFinish()) {
            for (Event event : blackBoard.getEvents()) {
                ShouWeiStateMathine mathine = ais.get(event.getOwnerId());
                mathine.execute(event);
            }
            blackBoard.go();
        }
    }

    public static void main(String[] args) {
        ShouWeiDiGong shouWeiDiGong = new ShouWeiDiGong();
        shouWeiDiGong.start();
    }

}
