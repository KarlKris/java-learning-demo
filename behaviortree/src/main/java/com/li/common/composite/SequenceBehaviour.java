package com.li.common.composite;

import com.li.common.behaviour.Behaviour;
import com.li.common.status.Status;

import java.util.Iterator;

/**
 * @Description 顺序器,依次执行每一个子行为，知道所有节点都成功执行或有一个子节点失败为止。  复合行为  控制节点
 * @Author li-yuanwen
 * @Date 2021/3/1 11:33
 */
public class SequenceBehaviour extends BaseComposite {

    @Override
    public final Status update() {
        Iterator<Behaviour> iterator =
                getChildren().iterator();
        // 有子行为
        if (!iterator.hasNext()) {
            return Status.INVALID;
        }

        while (true) {
            Behaviour behaviour = iterator.next();
            Status status = behaviour.tick();

            if (status != Status.SUCCESS) {
                return status;
            }

            if (!iterator.hasNext()) {
                return Status.SUCCESS;
            }
        }
    }

}
