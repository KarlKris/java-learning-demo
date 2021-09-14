package com.rank;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author li-yuanwen
 * 排行榜单个内容
 */
@Getter
@NoArgsConstructor
public class RankItem implements Comparable<RankItem> {

    /** 玩家id **/
    private long playerId;

    /** 排行榜值 **/
    private long value;

    /** 数据生成时间 **/
    private long time;


    public RankItem(long playerId, long value) {
        this.playerId = playerId;
        this.value = value;
        this.time = System.currentTimeMillis();
    }

    @Override
    public int compareTo(RankItem o) {
        // 倒序
        long sub = o.value - this.value;
        if (sub == 0) {
            return (int) (this.time - o.time);
        }
        return sub > 0 ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RankItem)) {
            return false;
        }

        RankItem rankItem = (RankItem) o;

        return playerId == rankItem.playerId;
    }

    @Override
    public int hashCode() {
        return (int) (playerId ^ (playerId >>> 32));
    }
}
