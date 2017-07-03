package com.modelncode.crudpattern.infrastructure.repository.jdbc.factory;

import com.modelncode.crudpattern.domain.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by g on 2017-07-03.
 */
public class ContactFactory {

    public static Contact createBy(ResultSet rs) throws SQLException {

        if (rs == null) return null;
        Contact contact = new Contact();
        contact.setContactType(rs.getString("contact_type"));
        contact.setEmail(rs.getString("email"));
        contact.setPhone(rs.getString("phone"));

        return contact;
    }
}
