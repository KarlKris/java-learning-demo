package com.li.starter.condition;

import cn.hutool.core.util.RandomUtil;
import com.li.common.condition.BaseCondition;
import com.li.common.status.Status;

/**
 * @Description 自身血量是否健康
 * @Author li-yuanwen
 * @Date 2021/3/4 20:24
 */
public class ConditionIsHealthLow extends BaseCondition {

    @Override
    public boolean valid() {
        return RandomUtil.randomBoolean();
    }


    @Override
    public Status update() {

        if (valid()) {
            System.out.println("ConditionIsHealthLow");
            return Status.SUCCESS;
        }
        System.out.println("ConditionIsNotHealthLow");
        return Status.FAILURE;
    }
}
