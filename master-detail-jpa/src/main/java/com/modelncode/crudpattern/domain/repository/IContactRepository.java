package com.modelncode.crudpattern.domain.repository;

import com.modelncode.crudpattern.domain.Contact;
import com.modelncode.crudpattern.domain.ContactId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by g on 2017-07-03.
 */
public interface IContactRepository extends JpaRepository<Contact, ContactId> {

}
