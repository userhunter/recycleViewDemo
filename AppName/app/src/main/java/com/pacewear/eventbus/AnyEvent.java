package com.pacewear.eventbus;

/**
 * Created by p_billylu on 2018/7/4.
 */

public class AnyEvent {

    private String discribe;
    private int name;
    private int age;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    //构造函数
    public AnyEvent(String discribe) {
        this.discribe = discribe;
    }

    //set/get方法
    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getDiscribe() {
        return discribe;
    }
}
