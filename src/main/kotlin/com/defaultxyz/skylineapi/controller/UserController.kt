package com.defaultxyz.skylineapi.controller

import com.defaultxyz.skylineapi.model.User
import com.defaultxyz.skylineapi.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val repository: UserRepository) {

    @GetMapping("/user/all")
    fun getAll(): List<User> = repository.findAll()
}