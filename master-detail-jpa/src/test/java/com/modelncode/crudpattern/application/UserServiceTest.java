package com.modelncode.crudpattern.application;

import com.modelncode.crudpattern.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by g on 2017-06-12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void test목록조회() {

    }

    @Test
    public void test조회() {

    }

    @Test
    public void test추가() {

    }

    @Test
    public void test수정() {

    }

    @Test
    public void test삭제() {

    }
}
