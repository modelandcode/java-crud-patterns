package com.modelncode.crudpattern.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public class Group {
    private long id;
    private String name;

    private List<User> users;

    public Group() {
        this.users = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
    public void addUser(User user) {
        this.users.add(user);
    }
}
