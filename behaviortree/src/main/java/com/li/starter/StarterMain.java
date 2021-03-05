package com.li.starter;

import com.li.common.BehaviourTree;
import com.li.common.BehaviourTreeBuilder;
import com.li.common.composite.ParallelBehaviour;
import com.li.common.composite.ParallelPolicy;
import com.li.common.composite.SelectorBehaviour;
import com.li.common.composite.SequenceBehaviour;
import com.li.starter.action.ActionAttack;
import com.li.starter.action.ActionPatrol;
import com.li.starter.action.ActionRunaway;
import com.li.starter.condition.ConditionIsEnemyDead;
import com.li.starter.condition.ConditionIsHealthLow;
import com.li.starter.condition.ConditionIsSeeEnemy;
import com.li.starter.decorator.RepeatDecorator;

/**
 * @Description 行为树新手包入口
 * @Author li-yuanwen
 * @Date 2021/3/1 11:32
 */
public class StarterMain {

    public static void main(String[] args) {
        BehaviourTreeBuilder builder = new BehaviourTreeBuilder();
        builder.root(new SelectorBehaviour());

        BehaviourTree tree = builder
                .addBehaviour(new SequenceBehaviour())
                    .addBehaviour(new ConditionIsSeeEnemy())
                        .back()
                    .addBehaviour(new SelectorBehaviour())
                        .addBehaviour(new SequenceBehaviour())
                            .addBehaviour(new ConditionIsHealthLow())
                                .back()
                            .addBehaviour(new ActionRunaway())
                                .back()
                            .back()

                        .addBehaviour(new ParallelBehaviour(ParallelPolicy.REQUICE_ALL, ParallelPolicy.REQUICE_ONE))
                            .addBehaviour(new ConditionIsEnemyDead())
                                .back()
                            .addBehaviour(new RepeatDecorator())
                                .addBehaviour(new ActionAttack())
                                    .back()
                                .back()
                            .back()
                        .back()
                    .back()
                .addBehaviour(new ActionPatrol())
                .build();


        //模拟执行行为树
        for (int i = 0; i < 10; ++i){
            System.out.println("--------------" + i + "------------");
            tree.start();
        }

    }
}
