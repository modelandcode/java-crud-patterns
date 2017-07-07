package com.modelncode.crudpattern.application.impl;

import com.modelncode.crudpattern.application.IGroupService;
import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.User;
import com.modelncode.crudpattern.domain.exception.NotAffectedException;
import com.modelncode.crudpattern.domain.repository.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Group> groups = new ArrayList<>();
        groupRepository.findAll().iterator().forEachRemaining(groups::add);
        return groups;
    }

    @Override
    public Group 조회(long id) {
        return groupRepository.findOne(id);
    }

    @Override
    public Group 추가(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void 수정(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void 삭제(long id) {
        groupRepository.delete(id);

    }
}
