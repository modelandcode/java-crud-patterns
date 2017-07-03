package com.modelncode.crudpattern.domain.repository;

import com.modelncode.crudpattern.domain.Contact;

import java.util.List;

/**
 * Created by g on 2017-07-03.
 */
public interface IContactRepository {
    List<Contact> 목록조회(long userId);
    int 추가(long userId, Contact contact);
    //int 수정(long userId, Contact contact);
    //int 삭제(long userId, Contact contact);
    void 일괄삭제(long userId);
}
