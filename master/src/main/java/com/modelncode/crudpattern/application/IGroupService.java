package com.modelncode.crudpattern.application;

import com.modelncode.crudpattern.domain.Group;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public interface IGroupService {
    List<Group> 목록조회();
    Group 조회(long id);
    Group 추가(Group group);
    void 수정(Group group);
    void 삭제(long id);
}
