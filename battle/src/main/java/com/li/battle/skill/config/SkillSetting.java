package com.li.battle.skill.config;

/**
 * 技能配置表
 * @author li-yuanwen
 */
public class SkillSetting {

    /** 技能标识 **/
    private int id;

    /** 技能类型 **/
    private byte type;

    /** 目标选择表#id **/
    private int selectId;

    /** 效果表#id **/
    private int effectId;

    /** 前摇时长(毫秒) **/
    private int castPoint;

    /** 后摇时长(毫秒) **/
    private int backPoint;

    /** 技能CD(毫秒) **/
    private int cd;

}
