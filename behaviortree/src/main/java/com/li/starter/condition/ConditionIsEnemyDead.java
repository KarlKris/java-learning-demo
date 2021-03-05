package com.li.starter.condition;

import cn.hutool.core.util.RandomUtil;
import com.li.common.condition.BaseCondition;
import com.li.common.status.Status;

/**
 * @Description 敌人是否死亡
 * @Author li-yuanwen
 * @Date 2021/3/4 20:23
 */
public class ConditionIsEnemyDead extends BaseCondition {

    @Override
    public boolean valid() {
        return RandomUtil.randomBoolean();
    }


    @Override
    public Status update() {
        if (valid()) {
            System.out.println("ConditionIsEnemyDead");
            return Status.SUCCESS;
        }
        System.out.println("ConditionIsNotEnemyDead");
        return Status.FAILURE;
    }
}
