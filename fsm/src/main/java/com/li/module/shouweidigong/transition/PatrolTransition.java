package com.li.module.shouweidigong.transition;

import com.li.event.Event;
import com.li.event.EventConstant;
import com.li.map.Direction;
import com.li.map.GameMap;
import com.li.map.Point;
import com.li.module.shouweidigong.BlackBoard;
import com.li.module.shouweidigong.ShouWeiDiGongConstants;
import com.li.module.shouweidigong.event.CharseInfo;
import com.li.module.shouweidigong.event.PatrolInfo;
import com.li.module.shouweidigong.model.Guard;
import com.li.state.State;
import com.li.transition.Transition;

import java.util.List;

/**
 * @author li-yuanwen
 * 巡逻动作处理
 */
public class PatrolTransition extends Transition<PatrolInfo> {

    public PatrolTransition(EventConstant eventConstant, State currentState, State nextState) {
        super(eventConstant.name(), currentState, nextState);
    }

    @Override
    protected boolean doExecute(int id, int time, PatrolInfo body, GameMap map, BlackBoard blackBoard) {
        Guard guard = body.getOwner();
        Point curPoint = guard.getCurPoint();
        Direction direction = body.getDirection();
        Point targetPoint = curPoint.calNewPoint(direction, ShouWeiDiGongConstants.GUARD_PATROL_SPEED);

        System.out.println(
                "回合开始[" + time + "]：巡逻前：守卫[" + id + "]当前位置" + curPoint + " 行进方向[" + direction.name() + "] 目标位置"
                        + targetPoint);

        List<Point> points = map.pathFinding(curPoint.getX(), curPoint.getY()
                , targetPoint.getX(), targetPoint.getY());

        int step = ShouWeiDiGongConstants.GUARD_PATROL_SPEED;

        do {
            // 本回合能到达的位置
            Point point = points.get(step);
            // 更新位置
            guard.updatePoint(point);
        } while (guard.isOutOfRange() && --step > 0);

        // 不可达
        boolean unable = step <= 0;
        if (unable) {
            guard.updatePoint(curPoint);
            // 随机一个方向
            direction = Direction.randomDirectionUnless(direction);
        }

        // 更新地图
        map.updatePoint(curPoint, ShouWeiDiGongConstants.COMMON_ID);
        map.updatePoint(guard.getCurPoint(), ShouWeiDiGongConstants.GUARD_ID);

        System.out.println(
                "回合[" + time + "]：巡逻执行后：守卫[" + id + "]当前位置" + guard.getCurPoint() + " 行进方向[" + direction.name() + "]");

        for (Direction d : Direction.values()) {
            // 判断能否看见盗贼
            for (Point point : guard.getCurPoint().calRoutes(d, ShouWeiDiGongConstants.GUARD_VISION_RANGE)) {
                if (!map.hasByte(point, ShouWeiDiGongConstants.THIEVES_ID)) {
                    continue;
                }

                System.out.println("回合[" + time + "]：巡逻结束前：守卫[" + id + "]发现敌人,敌人位置" + point);

                Event<CharseInfo> event = new Event<>(EventConstant.FIND,
                        new CharseInfo(point, guard), id, time + 1);
                blackBoard.post(event);
                return true;
            }
        }



        Event<PatrolInfo> event = new Event<>(EventConstant.PATROL,
                new PatrolInfo(direction, guard), id, time + 1);
        blackBoard.post(event);
        return false;
    }

}
