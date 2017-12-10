package com.modelncode.crudpattern.presentation;

import com.modelncode.crudpattern.application.IGroupService;
import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.exception.EmptyListException;
import com.modelncode.crudpattern.domain.exception.NotFoundException;
import com.modelncode.crudpattern.presentation.dto.OptionDto;
import com.modelncode.crudpattern.presentation.dto.OptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
@RestController
@RequestMapping("/api")
public class GroupController {
    public static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    private IGroupService GroupService;

    @Autowired
    public GroupController(IGroupService GroupService) {
        Assert.notNull(GroupService, "GroupService 개체가 반드시 필요!");
        this.GroupService = GroupService;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<Group> 목록조회() {
        List<Group> 목록 = GroupService.목록조회();

        if (목록 == null || 목록.size() == 0 )
            throw new EmptyListException("NO DATA");

        return 목록;
    }

    @RequestMapping(value = "/group-options", method = RequestMethod.GET)
    public List<OptionDto> 옵션목록조회() {
        List<Group> groups = GroupService.목록조회();

        if (groups == null || groups.size() == 0 )
            throw new EmptyListException("NO DATA");

        List<OptionDto> options = new ArrayList<>();
        for(Group group: groups) {
            options.add(OptionFactory.createBy(group));
        }

        return options;
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public Group 조회(@PathVariable int id) {

        Group Group  = GroupService.조회(id);
        if (Group == null) {
            logger.error("NOT FOUND ID: ", id);
            throw new NotFoundException(id + " 사용자 정보를 찾을 수 없습니다.");
        }

        return Group;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public Group 추가(@RequestBody Group Group) {
        return GroupService.추가(Group);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.PUT)
    public void 수정(@RequestBody Group Group) {
        GroupService.수정(Group);
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
    public void 삭제(@PathVariable int id) {
        GroupService.삭제(id);
    }
}
