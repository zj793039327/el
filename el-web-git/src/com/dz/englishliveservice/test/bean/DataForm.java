package com.dz.englishliveservice.test.bean;


public class DataForm implements java.io.Serializable
{
    private static final long serialVersionUID = -447936555639261615L;
    private String name = "bill";
    private int age = 20;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}