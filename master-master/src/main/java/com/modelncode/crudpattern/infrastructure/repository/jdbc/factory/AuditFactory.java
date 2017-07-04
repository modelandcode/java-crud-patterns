package com.modelncode.crudpattern.infrastructure.repository.jdbc.factory;

import com.modelncode.crudpattern.domain.Audit;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by g on 2017-07-04.
 */
public class AuditFactory {
    public static Audit createBy(ResultSet rs) throws SQLException {
        if (rs == null) return null;

        Audit audit = new Audit();
        audit.setId(rs.getLong("id"));
        audit.setAuditDt(rs.getTimestamp("audit_dt").toLocalDateTime());
        audit.setTarget(rs.getString("target"));
        audit.setAction(rs.getString("action"));
        audit.setKeyId(rs.getLong("key_id"));
        audit.setDataNew(rs.getString("data_new"));
        audit.setDataOld(rs.getString("data_old"));
        audit.setUpdatedBy(rs.getString("updated_by"));

        return audit;
    }
}
