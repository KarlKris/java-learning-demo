package model;

import lombok.Getter;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/8 12:00
 */
@Getter
public class Animal {

    /** name **/
    private String name;

    public Animal(String name) {
        this.name = name;
    }
}
