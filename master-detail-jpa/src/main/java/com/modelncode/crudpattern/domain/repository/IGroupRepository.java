package com.modelncode.crudpattern.domain.repository;

import com.modelncode.crudpattern.domain.Group;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public interface IGroupRepository {
    List<Group> 목록조회();
    Group 조회(long id);
    long 추가(Group 그룹);
    int 수정(Group 그룹);
    int 삭제(long id);
}
