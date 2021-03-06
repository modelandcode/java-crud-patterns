package com.modelncode.crudpattern.presentation;

import com.modelncode.crudpattern.application.IGroupService;
import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.exception.EmptyListException;
import com.modelncode.crudpattern.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
@RestController
@RequestMapping("/api")
public class GroupController {
    public static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    private IGroupService groupService;

    @Autowired
    public GroupController(IGroupService groupService) {
        Assert.notNull(groupService, "GroupService 개체가 반드시 필요!");
        this.groupService = groupService;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<Group> 목록조회() {
        List<Group> 목록 = groupService.목록조회();

        if (목록 == null || 목록.size() == 0 )
            throw new EmptyListException("NO DATA");

        return 목록;
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public Group 조회(@PathVariable int id) {

        Group group  = groupService.조회(id);
        if (group == null) {
            logger.error("NOT FOUND ID: ", id);
            throw new NotFoundException(id + " 사용자 정보를 찾을 수 없습니다.");
        }

        return group;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public Group 추가(@RequestBody Group group) {
        return groupService.추가(group);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.PUT)
    public void 수정(@RequestBody Group group) {
        groupService.수정(group);
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
    public void 삭제(@PathVariable int id) {
        groupService.삭제(id);
    }
}
