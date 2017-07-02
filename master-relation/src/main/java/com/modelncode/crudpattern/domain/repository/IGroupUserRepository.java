package com.modelncode.crudpattern.domain.repository;

import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.GroupUser;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public interface IGroupUserRepository {
    List<GroupUser> 목록조회(long groupId);
    int 추가(GroupUser groupUser);
    int 수정(GroupUser groupUser);
    int 삭제(long groupId, long userId);
}
