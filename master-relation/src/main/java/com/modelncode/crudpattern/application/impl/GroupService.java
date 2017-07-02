package com.modelncode.crudpattern.application.impl;

import com.modelncode.crudpattern.application.IGroupService;
import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.GroupUser;
import com.modelncode.crudpattern.domain.User;
import com.modelncode.crudpattern.domain.exception.NotAffectedException;
import com.modelncode.crudpattern.domain.repository.IGroupRepository;
import com.modelncode.crudpattern.domain.repository.IGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
@Service
public class GroupService implements IGroupService {

    private IGroupRepository groupRepository;
    private IGroupUserRepository groupUserRepository;

    @Autowired
    public GroupService(IGroupRepository groupRepository, IGroupUserRepository groupUserRepository) {
        this.groupRepository = groupRepository;
        this.groupUserRepository = groupUserRepository;
    }

    @Override
    public List<Group> 목록조회() {
        return groupRepository.목록조회();
    }

    @Override
    public Group 조회(long id) {
        return groupRepository.조회(id);
    }

    @Override
    public Group 추가(Group Group) {
        long id = groupRepository.추가(Group);
        if (id == 0)
            throw new NotAffectedException("추가 처리 결과가 없습니다.");

        Group.setId(id);
        return Group;
    }

    @Override
    public void 수정(Group Group) {
        int affected = groupRepository.수정(Group);
        if (affected == 0)
            throw new NotAffectedException("수정 처리 결과가 없습니다.");
    }

    @Override
    public void 삭제(long id) {
        int affected = groupRepository.삭제(id);
        if (affected == 0)
            throw new NotAffectedException("삭제 처리 결과가 없습니다.");

    }

    @Override
    public List<GroupUser> 그룹사용자목록조회(long groupId) {
        List<GroupUser> groupUsers  = groupUserRepository.목록조회(groupId);
        if (groupUsers == null)
            return null;
        return groupUsers;
    }

    @Override
    public void 그룹사용자추가(GroupUser groupUser) {
        this.groupUserRepository.추가(groupUser);
    }

    @Override
    public void 그룹사용자수정(GroupUser groupUser) {
        this.groupUserRepository.수정(groupUser);
    }

    @Override
    public void 그룹사용자삭제(long groupId, long userId) {
        this.groupUserRepository.삭제(groupId, userId);
    }


}
