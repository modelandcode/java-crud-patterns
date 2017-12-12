package com.modelncode.crudpattern.application

import com.modelncode.crudpattern.domain.User

interface IUserService {
    List<User> 목록조회()
    User 조회(long id)
    User 추가(User 사용자)
    void 수정(User 사용자)
    void 삭제(long id)
}