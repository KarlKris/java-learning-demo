package com.li.common.composite;

import com.li.common.behaviour.BaseBehaviour;
import com.li.common.behaviour.Behaviour;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/2/28 22:31
 * @Description: 基础复合行为  控制节点
 **/
public abstract class BaseComposite extends BaseBehaviour implements Composite {

    private List<Behaviour> children;

    @Override
    public void addChild(Behaviour behaviour) {
        children.add(behaviour);
    }

    @Override
    public void removeChild(Behaviour behaviour) {
        children.remove(behaviour);
    }

    @Override
    public void clearChildren() {
        children.clear();
    }

    public List<Behaviour> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
