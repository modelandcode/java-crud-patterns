package com.modelncode.crudpattern.application.impl;

import com.modelncode.crudpattern.application.IUserService;
import com.modelncode.crudpattern.domain.User;
import com.modelncode.crudpattern.domain.exception.NotAffectedException;
import com.modelncode.crudpattern.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by g on 2017-06-12.
 */
@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> 목록조회() {
//        Iterable<User> iterator = userRepository.findAll();
//        List<User> users = StreamSupport
//                                .stream(iterator.spliterator(), false)
//                                .collect(Collectors.toList());
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator()
                        .forEachRemaining(users::add);
        return users;
    }

    @Override
    public User 조회(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User 추가(User user) {
        return userRepository.save(user);
    }

    @Override
    public void 수정(User user) {
        userRepository.save(user);
    }

    @Override
    public void 삭제(long id) {
        userRepository.delete(id);
    }
}
