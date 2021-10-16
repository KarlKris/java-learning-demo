package com.li.battle.buff.core;

/**
 * buff基本接口
 * @author li-yuanwen
 */
public interface Buff {

    /** 当Buff添加时，存在相同类型且施加者相同的时候，Buff执行刷新流程(更新Buff层数，等级，持续时间等数据) **/
    void onBuffRefresh();

    /** Buff实例化，加入容器后生效 **/
    void onBuffStart();

    /** 当Buff销毁前(还未从Buff容器中移除) **/
    void onBuffRemove();

    /** 当Buff销毁后(已从Buff容器中移除) **/
    void onBuffDestroy();
}
