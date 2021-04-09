package model;

import lombok.Data;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/3/25 10:27
 */
@Data
public class Person {

    /** name **/
    private String name;
    /** sex **/
    private String sex;
    /** age **/
    private int age;

    public void process() {
        System.out.println("name:" + name + "执行process方法！");
    }

    public void otherProcess() {
        System.out.println("name:" + name + "执行otherProcess方法！");
    }
}
