package com.modelncode.crudpattern.domain.repository;

import com.modelncode.crudpattern.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public interface IUserRepository extends CrudRepository<User, Long> {
}
