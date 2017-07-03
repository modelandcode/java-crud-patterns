package com.modelncode.crudpattern.application.impl;

import com.modelncode.crudpattern.application.IGroupService;
import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.exception.NotAffectedException;
import com.modelncode.crudpattern.domain.repository.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
@Service
public class GroupService implements IGroupService {

    private IGroupRepository GroupRepository;

    @Autowired
    public GroupService(IGroupRepository GroupRepository) {
        this.GroupRepository = GroupRepository;
    }

    @Override
    public List<Group> 목록조회() {
        return GroupRepository.목록조회();
    }

    @Override
    public Group 조회(long id) {
        return GroupRepository.조회(id);
    }

    @Override
    public Group 추가(Group Group) {
        long id = GroupRepository.추가(Group);
        if (id == 0)
            throw new NotAffectedException("추가 처리 결과가 없습니다.");

        Group.setId(id);
        return Group;
    }

    @Override
    public void 수정(Group Group) {
        int affected = GroupRepository.수정(Group);
        if (affected == 0)
            throw new NotAffectedException("수정 처리 결과가 없습니다.");
    }

    @Override
    public void 삭제(long id) {
        int affected = GroupRepository.삭제(id);
        if (affected == 0)
            throw new NotAffectedException("삭제 처리 결과가 없습니다.");

    }
}
