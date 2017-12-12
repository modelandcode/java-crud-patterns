package com.modelncode.crudpattern.presentation

import com.modelncode.crudpattern.application.IUserService
import com.modelncode.crudpattern.domain.User
import com.modelncode.crudpattern.domain.exception.EmptyListException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

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

    @PostMapping(value = "/users")
    User 추가(@Valid @RequestBody User user) {
        return userService.추가(user)
    }

    @GetMapping(value = "/users/{id}")
    ResponseEntity<User> 조회(@PathVariable long id) {
        User user  = userService.조회(id)
        if (!user) {
            log.error("NOT FOUND ID: ", id)
            //throw new NotFoundException(id + " 사용자 정보를 찾을 수 없습니다.")
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok().body(user)
    }

    @PutMapping(value = "/users/{id}")
    ResponseEntity<User> 수정(@PathVariable long id, @Valid @RequestBody User user) {
//        User dbUser  = userService.조회(id)
//        if (!dbUser)
//            return ResponseEntity.notFound().build()

        int affected = userService.수정(user)
        if (!affected)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user)
    }

    @DeleteMapping(value = "/users/{id}")
    ResponseEntity<User> 삭제(@PathVariable long id) {

//        User dbUser  = userService.조회(id)
//        if (!dbUser)
//            return ResponseEntity.notFound().build()

        int affected = userService.삭제(id)
        if (!affected)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok().build()
    }
}

