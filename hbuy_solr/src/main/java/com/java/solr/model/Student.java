package com.java.solr.model;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @Auther: Administrator
 * @Date: 2020-04-13 09:33
 * @Description:
 */
public class Student {
    //编号
    @Field
    private Integer xh;
    //姓名
    @Field
    private String name;
    //年龄
    @Field
    private Integer age;
    //性别
    @Field
    private String sex;

    public Student() {
    }

    public Student(Integer xh, String name, Integer age, String sex) {
        this.xh = xh;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "xh=" + xh +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
