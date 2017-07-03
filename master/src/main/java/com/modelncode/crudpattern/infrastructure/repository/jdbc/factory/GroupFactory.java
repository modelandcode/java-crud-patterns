package com.modelncode.crudpattern.infrastructure.repository.jdbc.factory;

import com.modelncode.crudpattern.domain.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by g on 2017-07-01.
 */
public class GroupFactory {
    public static Group createBy(ResultSet rs) throws SQLException {
        if (rs == null) return null;
        Group Group = new Group();
        Group.setId(rs.getLong("id"));
        Group.setName(rs.getString("name"));

        return Group;
    }
}
