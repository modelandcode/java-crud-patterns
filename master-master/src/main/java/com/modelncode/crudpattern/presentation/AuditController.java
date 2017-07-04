package com.modelncode.crudpattern.presentation;

import com.modelncode.crudpattern.application.IAuditService;
import com.modelncode.crudpattern.domain.Audit;
import com.modelncode.crudpattern.domain.exception.EmptyListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by g on 2017-07-04.
 */
@RestController
@RequestMapping("/api")
public class AuditController {
    public static final Logger logger = LoggerFactory.getLogger(AuditController.class);

    private IAuditService auditService;

    @Autowired
    public AuditController(IAuditService auditService) {
        Assert.notNull(auditService, "auditService 개체가 반드시 필요!");
        this.auditService = auditService;
    }

    @RequestMapping(value = "/audits", method = RequestMethod.GET)
    public List<Audit> 목록조회(@RequestParam("target") String target,
                                @RequestParam("keyId") long keyId) {
        List<Audit> 목록 = this.auditService.목록조회(target, keyId);

        if (목록 == null || 목록.size() == 0 )
            throw new EmptyListException("NO DATA");

        return 목록;
    }
}
