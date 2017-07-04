package com.modelncode.crudpattern.domain;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public class User {
    private long id;
    private String name;
    private String alias;
    private String email;
    private List<Contact> contacts;

    public User() {
        //this.contacts = new ArrayList<>();
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

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

//    public void addContact(Contact contact) {
//        this.contacts.add(contact);
//    }

}
