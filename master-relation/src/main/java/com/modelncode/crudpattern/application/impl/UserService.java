package com.modelncode.crudpattern.application.impl;

import com.modelncode.crudpattern.application.IUserService;
import com.modelncode.crudpattern.domain.User;
import com.modelncode.crudpattern.domain.exception.NotAffectedException;
import com.modelncode.crudpattern.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> 목록조회() {
        return userRepository.목록조회();
    }

    @Override
    public User 조회(long id) {
        return userRepository.조회(id);
    }

    @Override
    public User 추가(User user) {
        long id = userRepository.추가(user);
        if (id == 0)
            throw new NotAffectedException("추가 처리 결과가 없습니다.");

        user.setId(id);
        return user;
    }

    @Override
    public void 수정(User user) {
        int affected = userRepository.수정(user);
        if (affected == 0)
            throw new NotAffectedException("수정 처리 결과가 없습니다.");
    }

    @Override
    public void 삭제(long id) {
        int affected = userRepository.삭제(id);
        if (affected == 0)
            throw new NotAffectedException("삭제 처리 결과가 없습니다.");

    }
}
