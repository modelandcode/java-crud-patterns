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

    private IGroupRepository groupRepository;

    @Autowired
    public GroupService(IGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
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
    public Group 추가(Group group) {
        long id = groupRepository.추가(group);
        if (id == 0)
            throw new NotAffectedException("추가 처리 결과가 없습니다.");

        group.setId(id);
        return group;
    }

    @Override
    public void 수정(Group group) {
        int affected = groupRepository.수정(group);
        if (affected == 0)
            throw new NotAffectedException("수정 처리 결과가 없습니다.");
    }

    @Override
    public void 삭제(long id) {
        int affected = groupRepository.삭제(id);
        if (affected == 0)
            throw new NotAffectedException("삭제 처리 결과가 없습니다.");

    }
}
