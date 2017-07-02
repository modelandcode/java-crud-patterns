package com.modelncode.crudpattern.infrastructure.repository.jdbc;

import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.exception.RepositoryException;
import com.modelncode.crudpattern.domain.repository.IGroupRepository;
import com.modelncode.crudpattern.infrastructure.repository.jdbc.factory.GroupFactory;
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
public class GroupRepository implements IGroupRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Group> 목록조회() {
        StringBuilder sbSql =  new StringBuilder("SELECT * FROM MC_GROUP ");
        try {
            return this.jdbcTemplate.query(sbSql.toString(),
                    new Object[]{}, (rs, rowNum) -> GroupFactory.createBy(rs));
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public Group 조회(long id) {
        StringBuilder sbSql =  new StringBuilder("SELECT * FROM MC_Group WHERE id=?");
        try {
            return this.jdbcTemplate.queryForObject(	sbSql.toString(),
                    new Object[] { id }, (rs, rowNum) -> GroupFactory.createBy(rs) );
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public long 추가(Group group) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", group.getName());

        try {
            this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                    .withTableName("MC_GROUP")
                    .usingGeneratedKeyColumns("id");

            Number newId = simpleJdbcInsert.executeAndReturnKey(paramMap);
            return newId.longValue();
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 수정(Group group) {
        StringBuilder sbSql =  new StringBuilder("UPDATE MC_GROUP ");
        sbSql.append("SET name=? ");
        sbSql.append("WHERE id=?");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] { group.getName(), group.getId() });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 삭제(long id) {
        StringBuilder sbSql =  new StringBuilder("DELETE FROM MC_GROUP ");
        sbSql.append("WHERE id=?");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] { id });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }
}
