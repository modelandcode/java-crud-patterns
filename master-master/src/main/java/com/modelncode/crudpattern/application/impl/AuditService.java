package com.modelncode.crudpattern.application.impl;

import com.modelncode.crudpattern.application.IAuditService;
import com.modelncode.crudpattern.domain.Audit;
import com.modelncode.crudpattern.domain.repository.IAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by g on 2017-07-04.
 */
@Service
public class AuditService implements IAuditService {
    private IAuditRepository auditRepository;

    @Autowired
    public AuditService(IAuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public List<Audit> 목록조회(String target, long keyId) {
        return this.auditRepository.목록조회(target, keyId);
    }

    @Override
    public void 생성(String target, long keyId, String action, String dataNew, String dataOld) {
        Audit audit = new Audit();
        audit.setTarget(target);
        audit.setKeyId(keyId);
        audit.setAction(action);
        audit.setDataNew(dataNew);
        audit.setDataOld(dataOld);
        audit.setAuditDt(LocalDateTime.now());
        this.auditRepository.추가(audit);
    }
}
