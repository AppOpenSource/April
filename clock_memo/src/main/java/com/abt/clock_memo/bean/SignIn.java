package com.abt.clock_memo.bean;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * @描述： @SignIn
 * @作者： @黄卫旗
 * @创建时间： @2016/10/30
 */
public class SignIn implements Serializable {

    private static final long serialVersionUID = -5683263669918171030L;

    @DatabaseField(id=true)
    private String stuNO;
    @DatabaseField
    private String name;
    @DatabaseField
    private int age;
    @DatabaseField
    private String sex;
    @DatabaseField
    private double score;
    @DatabaseField
    private String address;
    @DatabaseField
    private String time;
    @DatabaseField
    private String status;

    public String getStuNO() {
        return stuNO;
    }

    public void setStuNO(String stuNO) {
        this.stuNO = stuNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
