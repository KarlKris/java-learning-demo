package com.rank.list;

import com.rank.RankItem;
import com.rank.RankList;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author li-yuanwen
 */
public class RankListByConcurrentSkipListSet implements RankList {


    private ConcurrentSkipListSet<RankItem> listSet = new ConcurrentSkipListSet<>(Comparator.reverseOrder());

    @Override
    public int getRankByPlayerId(long playerId) {
        return 0;
    }

    @Override
    public void update(long playerId, long curVal) {
        RankItem item = new RankItem(playerId, curVal);
        listSet.add(item);
    }

    @Override
    public List<RankItem> getSubList(int from, int to) {
        return null;
    }
}
