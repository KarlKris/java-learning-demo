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
public class SearchTransition extends Transition<SearchInfo> {

    public SearchTransition(EventConstant eventCode, State currentState, State nextState) {
        super(eventCode.name(), currentState, nextState);
    }

    @Override
    protected boolean doExecute(int id, int time, SearchInfo body, GameMap map, BlackBoard blackBoard) {
        Thieves thieves = body.getOwner();
        List<Point> routes = body.getRoutes();
        int size = routes.size();

        Point curPoint = thieves.getCurPoint();

        System.out.println("回合[" + time + "]：搜寻开始前：盗贼[" + id + "]位置" + curPoint);

        int nextIndex = body.getIndex() + ShouWeiDiGongConstants.THIEVES_SEARCH_SPEED;

        if (nextIndex >= size - 1) {
            // 玩法结束
            blackBoard.finish();

            System.out.println("盗贼[" + id + "]找到宝藏,游戏结束!!!");
            return false;
        }

        Point nextPoint = routes.get(nextIndex);
        map.updatePoint(nextPoint, ShouWeiDiGongConstants.THIEVES_ID);
        map.updatePoint(curPoint, ShouWeiDiGongConstants.COMMON_ID);

        thieves.updatePoint(nextPoint);

        System.out.println("回合[" + time + "]：搜寻执行后：盗贼[" + id + "]位置" + thieves.getCurPoint());

        for (Point point : nextPoint.calRoutes(routes.get(nextIndex - 1).calDirection(nextPoint)
                , ShouWeiDiGongConstants.THIEVES_VISION_RANGE)) {
            if (!map.hasByte(point, ShouWeiDiGongConstants.GUARD_ID)) {
                continue;
            }

            System.out.println("回合[" + time + "]：搜寻结束前：盗贼[" + id + "]发现守卫,守卫位置" + point);

            Event<EscapeInfo> event = new Event<>(EventConstant.DISCORVERED, new EscapeInfo(routes, nextIndex, thieves), id, time + 1);
            blackBoard.post(event);
            return true;
        }


        Event<SearchInfo> event = new Event<>(EventConstant.SEARCH,
                new SearchInfo(routes, nextIndex, thieves), id, time + 1);
        blackBoard.post(event);
        return false;
    }
}
