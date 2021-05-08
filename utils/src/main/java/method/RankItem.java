package method;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/27 16:49
 */
@Getter
@NoArgsConstructor
public  class RankItem implements Comparable<RankItem> {

    private long id;
    private long value;
    private long time;
    private String addition;

    @Override
    public int compareTo(RankItem o) {
        int sub;
        return (sub = Long.compare(o.value, value)) == 0 ? Long.compare(time, o.time) : sub;
    }

    public RankItem(long id, long value) {
        this.id = id;
        this.value = value;
        this.time = System.currentTimeMillis();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RankItem))
            return false;

        RankItem rankItem = (RankItem) o;

        return id == rankItem.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
