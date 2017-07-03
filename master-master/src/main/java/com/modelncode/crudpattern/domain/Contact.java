package com.modelncode.crudpattern.domain;

/**
 * Created by g on 2017-06-12.
 */
public class Contact {
    private String contactType;
    private String email;
    private String phone;

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

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
