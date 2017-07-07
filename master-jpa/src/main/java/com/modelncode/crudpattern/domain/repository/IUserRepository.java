package com.modelncode.crudpattern.domain.repository;

import com.modelncode.crudpattern.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by g on 2017-06-12.
 */
public interface IUserRepository extends CrudRepository<User, Long> {
}
