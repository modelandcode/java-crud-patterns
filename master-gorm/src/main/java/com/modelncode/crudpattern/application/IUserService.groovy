package com.modelncode.crudpattern.application

import com.modelncode.crudpattern.domain.User
import org.springframework.transaction.annotation.Transactional

interface IUserService {
    List<User> 목록조회()
    User 조회(long id)

    @Transactional
    User 추가(User 사용자)
    @Transactional
    void 수정(User 사용자)
    @Transactional
    void 삭제(long id)
}