package com.modelncode.crudpattern.infrastructure.repository.jdbc;

import com.modelncode.crudpattern.domain.Contact;
import com.modelncode.crudpattern.domain.exception.RepositoryException;
import com.modelncode.crudpattern.domain.repository.IContactRepository;
import com.modelncode.crudpattern.infrastructure.repository.jdbc.factory.ContactFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by g on 2017-07-03.
 */
@Repository
public class ContactRepository implements IContactRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Contact> 목록조회(long userId) {
        StringBuilder sbSql =  new StringBuilder("SELECT * FROM MC_CONTACT ");
        sbSql.append("where user_id=?");
        try {
            return this.jdbcTemplate.query(sbSql.toString(),
                    new Object[]{userId}, (rs, rowNum) -> ContactFactory.createBy(rs));
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 추가(long userId, Contact contact) {
        StringBuilder sbSql =  new StringBuilder("INSERT INTO MC_CONTACT ");
        sbSql.append("values(?, ?, ?, ?)");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] { userId,
                            contact.getContactType(),
                            contact.getEmail(),
                            contact.getPhone() });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }
/*
    @Override
    public int 수정(long userId, Contact contact) {
        StringBuilder sbSql =  new StringBuilder("UPDATE MC_CONTACT ");
        sbSql.append("SET contact_type=?, email=?, phone=? ");
        sbSql.append("WHERE user_id=?");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] { contact.getContactType(),
                                    contact.getEmail(),
                                    contact.getPhone(),
                                    userId });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public int 삭제(long userId, Contact contact) {
        StringBuilder sbSql =  new StringBuilder("DELETE FROM MC_CONTACT ");
        sbSql.append("WHERE user_id=? AND contact_type=?");
        try {
            return this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] { userId, contact.getContactType() });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }
*/
    @Override
    public void 일괄삭제(long userId) {
        StringBuilder sbSql =  new StringBuilder("DELETE FROM MC_CONTACT ");
        sbSql.append("WHERE user_id=?");
        try {
            this.jdbcTemplate.update(sbSql.toString(),
                    new Object[] { userId });
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

}
