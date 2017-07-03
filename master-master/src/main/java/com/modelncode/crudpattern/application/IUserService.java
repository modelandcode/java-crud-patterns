package com.modelncode.crudpattern.application;

import com.modelncode.crudpattern.domain.User;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public interface IUserService {
    List<User> 목록조회();
    User 조회(long id);
    User 추가(User 사용자);
    void 수정(User 사용자);
    void 삭제(long id);

}
