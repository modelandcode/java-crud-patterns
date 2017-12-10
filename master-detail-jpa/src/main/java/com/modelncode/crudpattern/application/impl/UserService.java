package com.modelncode.crudpattern.application.impl;

import com.modelncode.crudpattern.application.IUserService;
import com.modelncode.crudpattern.domain.Contact;
import com.modelncode.crudpattern.domain.User;
import com.modelncode.crudpattern.domain.exception.NotAffectedException;
import com.modelncode.crudpattern.domain.repository.IContactRepository;
import com.modelncode.crudpattern.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IContactRepository contactRepository;

    @Autowired
    public UserService(IUserRepository userRepository, IContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public List<User> 목록조회() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator()
                .forEachRemaining(users::add);
        return users;
    }

    @Override
    public User 조회(long id) {
        User user= userRepository.findOne(id);
        user.setContacts(contactRepository.목록조회(id));

        return user;
    }

    @Override
    public User 추가(User user) {
        User 저장User = userRepository.save(user);

        List<Contact> contacts = user.getContacts();
        if (contacts != null) {
            for(Contact contact: contacts) {
                contactRepository.추가(저장User.getId(), contact);
            }
        }

        return user;
    }

    @Override
    public void 수정(User user) {
        userRepository.save(user);

        List<Contact> contacts = user.getContacts();
        if (contacts != null) {
            contactRepository.일괄삭제(user.getId());
            for(Contact contact: contacts) {
                contactRepository.추가(user.getId(), contact);
            }
        }
    }

    @Override
    public void 삭제(long id) {
        contactRepository.일괄삭제(id);
        userRepository.delete(id);
    }
}
