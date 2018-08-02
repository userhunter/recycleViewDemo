package com.pacewear.eventbus;

/**
 * Created by p_billylu on 2018/7/5.
 */

public class SecondEvent {
    private int data;
    private String age;

    public SecondEvent(int data, String age) {
        this.data = data;
        this.age = age;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
