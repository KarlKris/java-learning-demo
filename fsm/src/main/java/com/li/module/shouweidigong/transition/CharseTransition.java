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
 * 发现盗贼逻辑
 */
public class CharseTransition extends Transition<CharseInfo> {

    public CharseTransition(EventConstant eventCode, State currentState, State nextState) {
        super(eventCode.name(), currentState, nextState);
    }

    @Override
    protected boolean doExecute(int id, int time, CharseInfo body, GameMap map, BlackBoard blackBoard) {
        Guard guard = body.getOwner();
        Point curPoint = guard.getCurPoint();
        Point targetPoint = body.getTargetPoint();

        System.out.println(
                "回合开始[" + time + "]：追逐前：守卫[" + id + "]当前位置 " + curPoint + " 追逐目标位置"
                        + targetPoint);

        List<Point> points = map.pathFinding(curPoint.getX(), curPoint.getY()
                , targetPoint.getX(), targetPoint.getY());

        int step = Math.min(ShouWeiDiGongConstants.GUARD_CHARSE_SPEED, points.size() - 1);

        do {
            // 本回合能到达的位置
            Point point = points.get(step);
            // 更新位置
            guard.updatePoint(point);

            if (map.hasByte(guard.getCurPoint(), ShouWeiDiGongConstants.THIEVES_ID)) {
                // 玩法结束
                blackBoard.finish();
                System.out.println("守卫[" + id + "]捉到盗贼,游戏结束!!!");
                return false;
            }

        } while (guard.isOutOfRange() && --step > 0);

        // 不可达
        boolean unable = step <= 0;
        if (unable) {
            guard.updatePoint(curPoint);
        }
        // 更新地图
        map.updatePoint(curPoint, ShouWeiDiGongConstants.COMMON_ID);
        map.updatePoint(guard.getCurPoint(), ShouWeiDiGongConstants.GUARD_ID);

        System.out.println(
                "回合[" + time + "]：追逐后：守卫[" + id + "]当前位置" + guard.getCurPoint());

        // 判断能否看见盗贼
        Direction direction = guard.getCurPoint().calDirection(targetPoint);

        System.out.println("回合[" + time + "]：搜寻盗贼方向：" + direction.name());

        for (Point point : guard.getCurPoint().calRoutes(direction, ShouWeiDiGongConstants.GUARD_VISION_RANGE)) {
            if (!map.hasByte(point, ShouWeiDiGongConstants.THIEVES_ID)) {
                continue;
            }

            System.out.println("回合[" + time + "]：追逐结束前：守卫[" + id + "]发现敌人,敌人位置" + point);

            Event<CharseInfo> event = new Event<>(EventConstant.FIND,
                    new CharseInfo(point, guard), id, time + 1);
            blackBoard.post(event);

            return false;
        }

        Event<PatrolInfo> event = new Event<>(EventConstant.PATROL,
                new PatrolInfo(Direction.randomDirection(), guard), id, time + 1);
        blackBoard.post(event);

        return true;
    }
}
