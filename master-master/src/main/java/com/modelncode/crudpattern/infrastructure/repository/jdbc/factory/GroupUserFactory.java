package com.modelncode.crudpattern.infrastructure.repository.jdbc.factory;

import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.GroupUser;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by g on 2017-07-01.
 */
public class GroupUserFactory {
    public static GroupUser createBy(ResultSet rs) throws SQLException {

        if (rs == null) return null;
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupId(rs.getLong("group_id"));
        groupUser.setUserId(rs.getLong("user_id"));
        groupUser.setUserName(rs.getString("user_name"));
        groupUser.setPosition(rs.getString("position"));

        return groupUser;
    }
}
