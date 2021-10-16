package com.li.battle.buff.core.impl;

import com.li.battle.buff.core.Buff;
import com.li.battle.core.context.BuffCreateContext;

/**
 * 所有buff的基类，包含各类成员函数和基本接口
 * @author li-yuanwen
 */
public abstract class AbstractBuff implements Buff {

    /** buff唯一标识 **/
    protected int buffId;

    /** buff施加者 **/
    protected long caster;

    /** 当前挂载的目标 **/
    protected long parent;

    /** buff由哪个技能创建 **/
    protected int skillAbilityId;

    /** buff层数 **/
    protected int layer;

    /** buff等级 **/
    protected int level;

    /** buff失效时间(时长) **/
    protected long expire;

    /** buff创建时的一些相关上下文数据 **/
    protected BuffCreateContext context;




}
