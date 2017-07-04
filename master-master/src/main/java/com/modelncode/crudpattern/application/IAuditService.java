package com.modelncode.crudpattern.application;

import com.modelncode.crudpattern.domain.Audit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by g on 2017-07-04.
 */
public interface IAuditService {

    List<Audit> 목록조회(String target, long keyId);

    @Transactional
    void 생성(String target, long keyId, String action, String dataNew, String dataOld);
}
