package model;

import lombok.Getter;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/3/6 15:38
 * @Description: 反射获取的用户类
 **/
@Getter
public class User implements IUser {

    private String name;

    private String sex;

    private int age;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }



}
