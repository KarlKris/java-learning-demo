package com.li.battle.buff;

/**
 * buff效果执行器
 * @author li-yuanwen
 */
public interface BuffProcessor {

    /**
     * 检查当前Buff能否创建,一般是检测目标身上是否存在免疫该Buff的相关Buff
     * @return true 能创建
     */
    boolean onBeforeBuffCreate();

    /** 当Buff添加时，存在相同类型且施加者相同的时候，Buff执行刷新流程(更新Buff层数，等级，持续时间等数据) **/
    void onBuffRefresh();

    /** Buff实例化，加入容器后生效 **/
    void onBuffStart();

    /** 当Buff销毁前(还未从Buff容器中移除) **/
    void onBuffRemove();

    /** 当Buff销毁后(已从Buff容器中移除) **/
    void onBuffDestroy();



}
