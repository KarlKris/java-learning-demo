package com.li.common.composite;

import com.li.common.behaviour.Behaviour;
import com.li.common.status.Status;

import java.util.Iterator;

/**
 * @Description 并行器，根据并行策略执行子行为，复合行为，控制节点
 * @Author li-yuanwen
 * @Date 2021/3/1 11:50
 */
public class ParallelBehaviour extends BaseComposite {

    /** 成功并行策略 **/
    private ParallelPolicy successPolicy;
    /**失败并行策略  **/
    private ParallelPolicy failurePolicy;

    public ParallelBehaviour(ParallelPolicy successPolicy, ParallelPolicy failurePolicy) {
        this.successPolicy = successPolicy;
        this.failurePolicy = failurePolicy;
    }

    @Override
    public final Status update() {
        int successCount = 0;
        int failureCount = 0;

        int size = getChildren().size();

        Iterator<Behaviour> iterator =
                getChildren().iterator();

        if (!iterator.hasNext()) {
            return Status.INVALID;
        }

        while (iterator.hasNext()) {
            Behaviour behaviour = iterator.next();
            Status status = behaviour.tick();

            if (status == Status.SUCCESS) {
                successCount++;

                if (successPolicy == ParallelPolicy.REQUICE_ONE) {
                    return status;
                }
            }

            if (status == Status.FAILURE ) {
                failureCount++;

                if (failurePolicy == ParallelPolicy.REQUICE_ONE) {
                    return status;
                }

            }
        }

        if (failurePolicy == ParallelPolicy.REQUICE_ALL && failureCount == size) {
            return Status.FAILURE;
        }

        if (successPolicy == ParallelPolicy.REQUICE_ALL && successCount == size) {
            return Status.SUCCESS;
        }

        return Status.RUNNING;
    }
}
