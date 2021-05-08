package com.li.module.shouweidigong.transition;

import com.li.event.Event;
import com.li.event.EventConstant;
import com.li.map.Direction;
import com.li.map.GameMap;
import com.li.map.Point;
import com.li.module.shouweidigong.BlackBoard;
import com.li.module.shouweidigong.ShouWeiDiGongConstants;
import com.li.module.shouweidigong.event.PatrolInfo;
import com.li.module.shouweidigong.model.Guard;
import com.li.transition.Transition;

/**
 * @author li-yuanwen
 * 巡逻动作处理
 */
public class PatrolTransition extends Transition<PatrolInfo> {

    @Override
    protected boolean doExecute(int owerId, int time, PatrolInfo body, GameMap map, BlackBoard blackBoard) {
        Guard guard = body.getOwner();
        Point targetPoint = guard.getCurPoint().calNewPoint(body.getDirection(), ShouWeiDiGongConstants.GUARD_SPEED);
        // 单个回合不可达
        if (map.pathFinding(guard.getCurPoint().getX(), guard.getCurPoint().getY()
                , targetPoint.getX(), targetPoint.getY()).size() > ShouWeiDiGongConstants.GUARD_SPEED) {

        }
        // 超出范围
        if (guard.isOutOfRange()) {
            Event<PatrolInfo> event = new Event<>(EventConstant.PATROL,
                    new PatrolInfo(Direction.randomDirectionUnless(body.getDirection()), guard), owerId, time + 1);
            blackBoard.post(event);
            return false;
        }



        return false;
    }


}
