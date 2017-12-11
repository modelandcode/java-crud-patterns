package com.modelncode.crudpattern.presentation

import com.modelncode.crudpattern.application.IUserService
import com.modelncode.crudpattern.domain.User
import com.modelncode.crudpattern.domain.exception.EmptyListException
import com.modelncode.crudpattern.domain.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.*

@groovy.util.logging.Slf4j
@RestController
@RequestMapping("/api")
class UserController {

    private IUserService userService

    @Autowired
    UserController(IUserService userService) {
        Assert.notNull(userService, "userService 개체가 반드시 필요!")
        this.userService = userService
    }

    @GetMapping(value = "/users")
    List<User> 목록조회() {
        List<User> 목록 = userService.목록조회()

        if (!목록)
            throw new EmptyListException("NO DATA")

        return 목록
    }

    @GetMapping(value = "/users/{id}")
    User 조회(@PathVariable int id) {

        User user  = userService.조회(id)
        if (!user) {
            log.error("NOT FOUND ID: ", id)
            throw new NotFoundException(id + " 사용자 정보를 찾을 수 없습니다.")
        }

        return user
    }

    @PostMapping(value = "/users")
    User 추가(@RequestBody User user) {
        return userService.추가(user)
    }

    @PutMapping(value = "/users")
    void 수정(@RequestBody User user) {
        userService.수정(user)
    }

    @DeleteMapping(value = "/users/{id}")
    void 삭제(@PathVariable int id) {
        userService.삭제(id)
    }
}
