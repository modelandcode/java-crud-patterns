package com.modelncode.crudpattern.presentation;

import com.modelncode.crudpattern.application.IGroupService;
import com.modelncode.crudpattern.domain.GroupUser;
import com.modelncode.crudpattern.domain.exception.EmptyListException;
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
public class GroupUserController {
    public static final Logger logger = LoggerFactory.getLogger(GroupUserController.class);

    private IGroupService groupService;

    @Autowired
    public GroupUserController(IGroupService groupService) {
        Assert.notNull(groupService, "GroupService 개체가 반드시 필요!");
        this.groupService = groupService;
    }

    @RequestMapping(value = "/group-users/{groupId}", method = RequestMethod.GET)
    public List<GroupUser> 사용자목록조회(@PathVariable int groupId) {
        List<GroupUser> 목록 = this.groupService.그룹사용자목록조회(groupId);

        if (목록 == null || 목록.size() == 0 )
            throw new EmptyListException("NO DATA");

        return 목록;
    }


    @RequestMapping(value = "/group-users", method = RequestMethod.POST)
    public void 그룹사용자추가(@RequestBody GroupUser groupUser) {
        this.groupService.그룹사용자추가(groupUser);
    }

    @RequestMapping(value = "/group-users", method = RequestMethod.PUT)
    public void 그룹사용자수정(@RequestBody GroupUser groupUser) {
        this.groupService.그룹사용자수정(groupUser);
    }

    @RequestMapping(value = "/group-users", method = RequestMethod.DELETE)
    public void 그룹사용자삭제(@RequestBody GroupUser groupUser) {
        this.groupService.그룹사용자삭제(groupUser.getGroupId(), groupUser.getUserId());
    }
}
