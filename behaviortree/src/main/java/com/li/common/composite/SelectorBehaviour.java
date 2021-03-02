package com.li.common.composite;

import com.li.common.behaviour.Behaviour;
import com.li.common.status.Status;

import java.util.Iterator;

/**
 * @Description 选择器，依次执行每一个行为，直到它发现子节点已经成功执行或返回RUNNING状态  控制节点 复合行为
 * @Author li-yuanwen
 * @Date 2021/3/1 11:42
 */
public class SelectorBehaviour extends BaseComposite {

    @Override
    public final Status update() {
        Iterator<Behaviour> iterator =
                getChildren().iterator();

        if (!iterator.hasNext()) {
            return Status.INVALID;
        }

        while (true) {
            Behaviour behaviour = iterator.next();
            Status status = behaviour.tick();

            if (status != Status.FAILURE) {
                return status;
            }

            if (!iterator.hasNext()) {
                return Status.FAILURE;
            }
        }

    }

}
