package com.modelncode.crudpattern.mastergroovy.repository

import com.modelncode.crudpattern.domain.User
import com.modelncode.crudpattern.domain.repository.IUserRepository
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepository implements IUserRepository {

    @Autowired Sql sql

    @Override
    List<User> 목록조회() {
        def 목록 = []
        sql.eachRow(UserRepositorySql.목록조회, []) { row ->
            목록.add(new User(
                    id: row.id,
                    name: row.name,
                    alias: row.alias,
                    email: row.email
            ))
        }
        return 목록
    }

    @Override
    User 조회(long id) {
        def row = sql.firstRow(UserRepositorySql.조회, [id])
        if (row)
            return new User(
                    id: row.id,
                    name: row.name,
                    alias: row.alias,
                    email: row.email)

        return null
    }

    @Override
    long 추가(User 사용자) {
        def ids = sql.executeInsert(UserRepositorySql.추가, [
                사용자.name,
                사용자.alias,
                사용자.email ])

        return ids[0][0]
    }

    @Override
    int 수정(User 사용자) {
        return sql.executeUpdate(UserRepositorySql.수정, [
                사용자.name,
                사용자.alias,
                사용자.email,
                사용자.id
        ])
    }

    @Override
    int 삭제(long id) {
        return sql.executeUpdate(UserRepositorySql.삭제, [id])
    }
}
