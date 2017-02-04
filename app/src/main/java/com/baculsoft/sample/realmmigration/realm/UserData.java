package com.baculsoft.sample.realmmigration.realm;

import com.baculsoft.sample.realmmigration.model.User;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class UserData extends RealmObject {

    @PrimaryKey
    private String email;

    private String name;
    private int age;

    public UserData() {
    }

    public void fill(final User user) {
        setEmail(user.getEmail());
        setName(user.getName());
        setAge(user.getAge());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }
}