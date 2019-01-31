package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {

    fun findByEmail(email: String): User?
}