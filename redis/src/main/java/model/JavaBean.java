package model;

import lombok.Data;

import java.util.List;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/3/29 16:52
 */
@Data
public class JavaBean {

    private String name;

    private List<Integer> rank;

    public JavaBean() {
    }

    public JavaBean(String name, List<Integer> rank) {
        this.name = name;
        this.rank = rank;
    }


}
