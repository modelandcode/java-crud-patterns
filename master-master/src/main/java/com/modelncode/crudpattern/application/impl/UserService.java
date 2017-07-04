package com.modelncode.crudpattern.application.impl;

import com.modelncode.crudpattern.application.IAuditService;
import com.modelncode.crudpattern.application.IUserService;
import com.modelncode.crudpattern.domain.User;
import com.modelncode.crudpattern.domain.exception.NotAffectedException;
import com.modelncode.crudpattern.domain.repository.IUserRepository;
import com.modelncode.crudpattern.infrastructure.factory.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IAuditService auditService;

    @Autowired
    public UserService(IUserRepository userRepository,
                       IAuditService auditService) {
        this.userRepository = userRepository;
        this.auditService = auditService;
    }

    @Override
    public List<User> 목록조회() {
        return this.userRepository.목록조회();
    }

    @Override
    public User 조회(long id) {
        User user= this.userRepository.조회(id);

        return user;
    }

    @Override
    public User 추가(User user) {
        long id = userRepository.추가(user);
        if (id == 0)
            throw new NotAffectedException("추가 처리 결과가 없습니다.");
        user.setId(id);

        this.auditService.생성("USER", id, "INSERT", JsonFactory.toJson(user), null);

        return user;
    }

    @Override
    public void 수정(User user) {
        User oldUser = this.userRepository.조회(user.getId());

        int affected = this.userRepository.수정(user);
        if (affected == 0)
            throw new NotAffectedException("수정 처리 결과가 없습니다.");

        this.auditService.생성("USER", user.getId(), "UPDATE",
                                JsonFactory.toJson(user), JsonFactory.toJson(oldUser));
    }

    @Override
    public void 삭제(long id) {
        User oldUser = this.userRepository.조회(id);

        int affected = this.userRepository.삭제(id);
        if (affected == 0)
            throw new NotAffectedException("삭제 처리 결과가 없습니다.");

        this.auditService.생성("USER", id, "DELETE", null, JsonFactory.toJson(oldUser));

    }
}
