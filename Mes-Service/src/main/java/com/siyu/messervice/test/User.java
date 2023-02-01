package com.siyu.messervice.test;

import lombok.Data;

@Data
public class User {
    private String name;
    private String id;
    private String sex;
    private String idCard;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
