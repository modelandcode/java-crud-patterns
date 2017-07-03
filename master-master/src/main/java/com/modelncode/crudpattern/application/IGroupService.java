package com.modelncode.crudpattern.application;

import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.GroupUser;
import com.modelncode.crudpattern.domain.User;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public interface IGroupService {
    List<Group> 목록조회();
    Group 조회(long id);
    Group 추가(Group 그룹);
    void 수정(Group 그룹);
    void 삭제(long id);

    List<GroupUser> 그룹사용자목록조회(long groupId);
    void 그룹사용자추가(GroupUser groupUser);
    void 그룹사용자수정(GroupUser groupUser);
    void 그룹사용자삭제(long groupId, long userId);
}
