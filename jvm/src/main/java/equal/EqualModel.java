package equal;

import lombok.Data;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/3/8 22:34
 * @Description:
 **/
@Data
public class EqualModel {

    private int model;

    public EqualModel(int model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
