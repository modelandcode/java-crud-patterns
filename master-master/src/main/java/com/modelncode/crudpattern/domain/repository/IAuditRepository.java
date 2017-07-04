package com.modelncode.crudpattern.domain.repository;

import com.modelncode.crudpattern.domain.Audit;

import java.util.List;

/**
 * Created by g on 2017-07-04.
 */
public interface IAuditRepository {
    List<Audit> 목록조회(String target, long keyId);
    long 추가(Audit audit);
}
