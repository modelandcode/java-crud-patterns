package com.modelncode.crudpattern.application.impl

import com.modelncode.crudpattern.application.IUserService
import com.modelncode.crudpattern.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService  implements IUserService{

    @Autowired
    UserService() {

    }

    @Override
    List<User> 목록조회() {
        return User.list()
    }

    @Override
    User 조회(long id) {
        return User.get(id)
    }

    @Override
    User 추가(User user) {
        user.save()
        user
    }

    @Override
    void 수정(User user) {
        user.save()
    }

    @Override
    void 삭제(long id) {
        def user = User.get(id)
        user.delete()
    }
}
