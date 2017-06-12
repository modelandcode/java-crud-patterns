package com.modelncode.crudpattern.infrastructure.repository.jdbc;

import com.modelncode.crudpattern.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by g on 2017-06-12.
 */
public class UserFactory {
    public static User createBy(ResultSet rs) throws SQLException {
        if (rs == null) return null;
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setAlias(rs.getString("alias"));
        user.setEmail(rs.getString("email"));

        return user;
    }
}
