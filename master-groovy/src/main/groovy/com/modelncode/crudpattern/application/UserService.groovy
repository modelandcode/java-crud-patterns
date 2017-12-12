package com.modelncode.crudpattern.application

import com.modelncode.crudpattern.domain.User
import com.modelncode.crudpattern.domain.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService implements IUserService {

    private IUserRepository userRepository

    @Autowired
    UserService(IUserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    List<User> 목록조회() {
        return this.userRepository.목록조회()
    }

    @Override
    User 조회(long id) {
        return this.userRepository.조회(id)
    }

    @Override
    User 추가(User 사용자) {
        사용자.id = this.userRepository.추가(사용자)
        return 사용자
    }

    @Override
    void 수정(User 사용자) {
        this.userRepository.수정(사용자)
    }

    @Override
    void 삭제(long id) {
        this.userRepository.삭제(id)
    }
}
