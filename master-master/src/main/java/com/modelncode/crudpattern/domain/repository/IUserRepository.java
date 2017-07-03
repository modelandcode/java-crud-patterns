package com.modelncode.crudpattern.domain.repository;

import com.modelncode.crudpattern.domain.User;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public interface IUserRepository {
    List<User> 목록조회();
    User 조회(long id);
    long 추가(User 사용자);
    int 수정(User 사용자);
    int 삭제(long id);
}
