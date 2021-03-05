package com.li.starter.decorator;

import com.li.common.decorator.BaseDecorator;
import com.li.common.status.Status;

/**
 * @Description 重复
 * @Author li-yuanwen
 * @Date 2021/3/4 20:54
 */
public class RepeatDecorator extends BaseDecorator {

    private int limited = 3;
    private volatile int count = 0;

    @Override
    public Status update() {
        while (true) {
            behaviour.tick();
            switch (behaviour.getStatus()) {
                case RUNNING:
                    return Status.SUCCESS;
                case FAILURE:
                    return Status.FAILURE;
                default:
                    break;
            }
            if (++count >= limited) {
                return Status.SUCCESS;
            }
        }
    }

    @Override
    public void onInitialize() {
        count = 0;
    }
}
