package com.modelncode.crudpattern.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Created by g on 2017-06-12.
 */
public class Audit {
    private long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditDt;
    private String target;
    private String action;
    private long keyId;
    private String dataNew;
    private String dataOld;
    private String updatedBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getAuditDt() {
        return auditDt;
    }

    public void setAuditDt(LocalDateTime auditDt) {
        this.auditDt = auditDt;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getKeyId() {
        return keyId;
    }

    public void setKeyId(long keyId) {
        this.keyId = keyId;
    }

    public String getDataNew() {
        return dataNew;
    }

    public void setDataNew(String dataNew) {
        this.dataNew = dataNew;
    }

    public String getDataOld() {
        return dataOld;
    }

    public void setDataOld(String dataOld) {
        this.dataOld = dataOld;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
