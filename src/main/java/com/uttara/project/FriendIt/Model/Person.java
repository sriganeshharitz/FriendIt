package com.uttara.project.FriendIt.Model;

public class Person {
    private String password;

    public Person(String password) {
        this.password = password;
    }

    public Person() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
