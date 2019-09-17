package com.example.mapper.mybatisMap.entity;

public class Usersss {
    private int id;
    private String user_name;
    private String password;
    private int age;
    private String duoyu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDuoyu() {
        return duoyu;
    }

    public void setDuoyu(String duoyu) {
        this.duoyu = duoyu;
    }

    @Override
    public String toString() {
        return "Usersss{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", duoyu='" + duoyu + '\'' +
                '}';
    }
}
