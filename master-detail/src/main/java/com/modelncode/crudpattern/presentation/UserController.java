package com.modelncode.crudpattern.presentation;

import com.modelncode.crudpattern.application.IUserService;
import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.User;
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
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        Assert.notNull(userService, "userService 개체가 반드시 필요!");
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> 목록조회() {
        List<User> 목록 = userService.목록조회();

        if (목록 == null || 목록.size() == 0 )
            throw new EmptyListException("NO DATA");

        return 목록;
    }

    @RequestMapping(value = "/user-options", method = RequestMethod.GET)
    public List<OptionDto> 옵션목록조회() {
        List<User> users = userService.목록조회();

        if (users == null || users.size() == 0 )
            throw new EmptyListException("NO DATA");

        List<OptionDto> options = new ArrayList<>();
        for(User user: users) {
            options.add(OptionFactory.createBy(user));
        }

        return options;
    }
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User 조회(@PathVariable int id) {

        User user  = userService.조회(id);
        if (user == null) {
            logger.error("NOT FOUND ID: ", id);
            throw new NotFoundException(id + " 사용자 정보를 찾을 수 없습니다.");
        }

        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User 추가(@RequestBody User user) {
        return userService.추가(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public void 수정(@RequestBody User user) {
        userService.수정(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void 삭제(@PathVariable int id) {
        userService.삭제(id);
    }
}
