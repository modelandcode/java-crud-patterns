package com.modelncode.crudpattern.infrastructure.repository.jdbc;

import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.GroupUser;
import com.modelncode.crudpattern.domain.exception.RepositoryException;
import com.modelncode.crudpattern.domain.repository.IGroupRepository;
import com.modelncode.crudpattern.domain.repository.IGroupUserRepository;
import com.modelncode.crudpattern.infrastructure.repository.jdbc.factory.GroupFactory;
import com.modelncode.crudpattern.infrastructure.repository.jdbc.factory.GroupUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by g on 2017-07-01.
 */
@Repository
public class GroupUserRepository implements IGroupUserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<GroupUser> 목록조회(long groupId) {
        StringBuilder sbSql =  new StringBuilder("SELECT t1.*, t2.name as user_name FROM MC_GROUP_USER t1 ");
        sbSql.append("INNER JOIN MC_USER t2 on t1.user_id=t2.id ");
        sbSql.append("where t1.group_id=?");
        try {
            return this.jdbcTemplate.query(sbSql.toString(),
                    new Object[]{groupId}, (rs, rowNum) -> GroupUserFactory.createBy(rs));
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 추가(GroupUser groupUser) {
        StringBuilder sbSql =  new StringBuilder("INSERT INTO MC_GROUP_USER ");
        sbSql.append("values(?, ?, ?)");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] {  groupUser.getGroupId(),
                                    groupUser.getUserId(),
                                    groupUser.getPosition()});
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 수정(GroupUser groupUser) {
        StringBuilder sbSql =  new StringBuilder("UPDATE MC_GROUP_USER ");
        sbSql.append("SET position=? ");
        sbSql.append("WHERE group_id=? AND user_id=?");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] { groupUser.getPosition(),
                                    groupUser.getGroupId(),
                                    groupUser.getUserId()});
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 삭제(long groupId, long userId) {
        StringBuilder sbSql =  new StringBuilder("DELETE FROM MC_GROUP_USER ");
        sbSql.append("WHERE group_id=? AND user_id=?");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] { groupId, userId });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }
}
