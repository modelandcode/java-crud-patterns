package com.modelncode.crudpattern.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by g on 2017-07-07.
 */
@Embeddable
public class ContactId implements Serializable {
    @Column(name = "USER_ID", nullable = false)
    private long userId;

    @Column(name = "CONTACT_TYPE", nullable = false)
    private String contactType;

    public ContactId() {
    }

    public ContactId(Long userId, String contactType) {
        this.userId = userId;
        this.contactType = contactType;
    }

    public long getUserId() {
        return userId;
    }

//    public void setUserId(long userId) {
//        this.userId = userId;
//    }

    public String getContactType() {
        return contactType;
    }

//    public void setContactType(String contactType) {
//        this.contactType = contactType;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactId)) return false;
        ContactId that = (ContactId) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getContactType(), that.getContactType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getContactType());
    }
}
