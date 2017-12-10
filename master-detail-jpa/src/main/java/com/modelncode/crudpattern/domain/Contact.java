package com.modelncode.crudpattern.domain;

import javax.persistence.*;

/**
 * Created by g on 2017-06-12.
 */
@Entity
@Table(name = "MC_CONTACT")
public class Contact {

    @EmbeddedId
    private ContactId id;

    public ContactId getId() {
        return id;
    }

    public void setId(ContactId id) {
        this.id = id;
    }

    private String email;
    private String phone;

//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }
//
//    public String getContactType() {
//        return contactType;
//    }
//
//    public void setContactType(String contactType) {
//        this.contactType = contactType;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
