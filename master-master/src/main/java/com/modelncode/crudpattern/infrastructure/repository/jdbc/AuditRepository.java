package com.modelncode.crudpattern.infrastructure.repository.jdbc;

import com.modelncode.crudpattern.domain.Audit;
import com.modelncode.crudpattern.domain.exception.RepositoryException;
import com.modelncode.crudpattern.domain.repository.IAuditRepository;
import com.modelncode.crudpattern.infrastructure.repository.jdbc.factory.AuditFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by g on 2017-07-04.
 */
@Repository
public class AuditRepository implements IAuditRepository {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Audit> 목록조회(String target, long keyId) {
        StringBuilder sbSql =  new StringBuilder("SELECT * FROM MC_AUDIT ");
        sbSql.append("WHERE target=? and key_id=?");
        try {
            return this.jdbcTemplate.query(sbSql.toString(),
                    new Object[] {target, keyId}, (rs, rowNum) -> AuditFactory.createBy(rs));
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    @Override
    public long 추가(Audit audit) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("audit_dt", Timestamp.valueOf(audit.getAuditDt()));
        paramMap.put("target", audit.getTarget());
        paramMap.put("action", audit.getAction());
        paramMap.put("key_id", audit.getKeyId());
        paramMap.put("data_new", audit.getDataNew());
        paramMap.put("data_old", audit.getDataOld());
        paramMap.put("updated_by", audit.getUpdatedBy());

        try {
            this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                    .withTableName("MC_AUDIT")
                    .usingGeneratedKeyColumns("id");

            Number newId = simpleJdbcInsert.executeAndReturnKey(paramMap);
            return newId.longValue();
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }
}
