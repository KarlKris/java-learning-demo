package com.li.starter.condition;

import cn.hutool.core.util.RandomUtil;
import com.li.common.condition.BaseCondition;
import com.li.common.status.Status;

/**
 * @Description 是否看见敌人
 * @Author li-yuanwen
 * @Date 2021/3/1 16:57
 */
public class ConditionIsSeeEnemy extends BaseCondition {

    @Override
    public boolean valid() {
        return RandomUtil.randomBoolean();
    }


    @Override
    public Status update() {

        if (valid()) {
            System.out.println("ConditionIsSeeEnemy");
            return Status.SUCCESS;
        }
        System.out.println("ConditionIsNotSeeEnemy");
        return Status.FAILURE;
    }


}
