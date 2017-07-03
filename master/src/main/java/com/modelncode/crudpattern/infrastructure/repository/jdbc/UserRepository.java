package com.modelncode.crudpattern.infrastructure.repository.jdbc;

import com.modelncode.crudpattern.domain.User;
import com.modelncode.crudpattern.domain.exception.RepositoryException;
import com.modelncode.crudpattern.domain.repository.IUserRepository;
import com.modelncode.crudpattern.infrastructure.repository.jdbc.factory.UserFactory;
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
 * Created by g on 2017-06-12.
 */
@Repository
public class UserRepository implements IUserRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> 목록조회() {
        StringBuilder sbSql =  new StringBuilder("SELECT * FROM MC_USER ");
        try {
            return this.jdbcTemplate.query(sbSql.toString(),
                    new Object[]{}, (rs, rowNum) -> UserFactory.createBy(rs));
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public User 조회(long id) {
        StringBuilder sbSql =  new StringBuilder("SELECT * FROM MC_USER WHERE id=?");
        try {
            return this.jdbcTemplate.queryForObject(	sbSql.toString(),
                new Object[] { id }, (rs, rowNum) -> UserFactory.createBy(rs) );
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public long 추가(User user) {
//        StringBuilder sbSql =  new StringBuilder("INSERT INTO MC_USER(name, alias, email) ");
//        sbSql.append("VALUES(?, ?, ?)");
//
//        return this.jdbcTemplate.update(sbSql.toString(),
//                new Object[] { user.getName(), user.getAlias(), user.getEmail() });
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", user.getName());
        paramMap.put("alias", user.getAlias());
        paramMap.put("email", user.getEmail());
        try {
            this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                                                .withTableName("MC_USER")
                                                .usingGeneratedKeyColumns("id");

            Number newId = simpleJdbcInsert.executeAndReturnKey(paramMap);
            return newId.longValue();
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 수정(User user) {
        StringBuilder sbSql =  new StringBuilder("UPDATE MC_USER ");
        sbSql.append("SET name=?, alias=?, email=? ");
        sbSql.append("where id=?");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                new Object[] { user.getName(), user.getAlias(), user.getEmail(), user.getId() });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 삭제(long id) {
        StringBuilder sbSql =  new StringBuilder("DELETE FROM MC_USER ");
        sbSql.append("where id=?");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                                            new Object[] { id });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }
}
