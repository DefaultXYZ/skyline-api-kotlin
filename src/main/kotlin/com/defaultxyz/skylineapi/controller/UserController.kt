package com.defaultxyz.skylineapi.controller

import com.defaultxyz.skylineapi.model.Response
import com.defaultxyz.skylineapi.model.User
import com.defaultxyz.skylineapi.repository.UserRepository
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val repository: UserRepository) {

    @PostMapping("/user/register")
    fun registerUser(user: User): Response<User> {
        val existedUser = repository.findByEmail(user.email)
        existedUser?.let { return Response("User already exists") }
        repository.save(user).apply {
            return Response("User created", this)
        }
    }

    @GetMapping("/user/login")
    fun login(
            @Param("email") email: String,
            @Param("password") password: String
    ): Response<User> {
        val existedUser = repository.findByEmail(email) ?: return Response("User doesn't exists")
        return when (existedUser.password) {
            password -> Response("Login successful", existedUser)
            else -> Response("Password is incorrect", null)
        }
    }
}