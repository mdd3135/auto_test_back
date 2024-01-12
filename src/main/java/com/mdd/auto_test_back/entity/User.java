package com.mdd.auto_test_back.entity;

import java.util.Map;

public class User {
    private int id;
    private String name;
    private String pwd;
    private String number;
    private String classroom;
    private int type;

    public User(int id, String name, String pwd, String number, String classroom, int type) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.number = number;
        this.classroom = classroom;
        this.type = type;
    }

    public User(Map<String, String> map) {
        int id = 0;
        String name = "";
        String pwd = "";
        String classroom = "";
        String number = "";
        int type = 0;
        try {
            id = Integer.parseInt(map.get("id"));
        } catch (Exception e) {
        }
        try {
            name = map.get("name");
        } catch (Exception e) {
        }
        try {
            pwd = map.get("pwd");
        } catch (Exception e) {
        }
        try {
            classroom = map.get("classroom");
        } catch (Exception e) {
        }
        try {
            type = Integer.parseInt(map.get("type"));
        } catch (Exception e) {
        }
        try {
            number = map.get("number");
        } catch (Exception e) {
        }
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.classroom = classroom;
        this.type = type;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
