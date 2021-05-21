package com.li.module.shouweidigong.transition;

import com.li.event.Event;
import com.li.event.EventConstant;
import com.li.map.GameMap;
import com.li.map.Point;
import com.li.module.shouweidigong.BlackBoard;
import com.li.module.shouweidigong.ShouWeiDiGongConstants;
import com.li.module.shouweidigong.event.EscapeInfo;
import com.li.module.shouweidigong.event.SearchInfo;
import com.li.module.shouweidigong.model.Thieves;
import com.li.state.State;
import com.li.transition.Transition;

import java.util.List;

/**
 * @author li-yuanwen
 */
public class EscapeTransition extends Transition<EscapeInfo> {

    public EscapeTransition(EventConstant eventCode, State currentState, State nextState) {
        super(eventCode.name(), currentState, nextState);
    }

    @Override
    protected boolean doExecute(int id, int time, EscapeInfo body, GameMap map, BlackBoard blackBoard) {
        Thieves thieves = body.getOwner();
        Point curPoint = thieves.getCurPoint();
        int index = body.getIndex();
        List<Point> routes = body.getRoutes();

        System.out.println("回合[" + time + "]：逃跑前：盗贼[" + id + "]位置,开始逃跑" + curPoint);

        int nextIndex = index - ShouWeiDiGongConstants.THIEVES_ESCAPE_SPEED;
        // 防止溢出
        nextIndex = Math.max(0, nextIndex);

        Point nextPoint = routes.get(nextIndex);
        map.updatePoint(nextPoint, ShouWeiDiGongConstants.THIEVES_ID);
        map.updatePoint(curPoint, ShouWeiDiGongConstants.COMMON_ID);

        thieves.updatePoint(nextPoint);

        System.out.println("回合[" + time + "]：逃跑执行后：盗贼[" + id + "]位置" + thieves.getCurPoint());

        for (Point point : nextPoint.calRoutes(routes.get(nextIndex + 1).calDirection(nextPoint)
                , ShouWeiDiGongConstants.THIEVES_VISION_RANGE)) {
            if (!map.hasByte(point, ShouWeiDiGongConstants.GUARD_ID)) {
                continue;
            }

            System.out.println("回合[" + time + "]：逃跑结束前：盗贼[" + id + "]发现守卫,守卫位置" + point);

            Event<EscapeInfo> event = new Event<>(EventConstant.DISCORVERED, new EscapeInfo(routes, nextIndex, thieves), id, time + 1);
            blackBoard.post(event);
            return false;
        }

        Event<SearchInfo> event = new Event<>(EventConstant.SEARCH,
                new SearchInfo(routes, nextIndex, thieves), id, time + 1);
        blackBoard.post(event);

        return true;
    }
}
