package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

    fun findByEmail(email: String): User?
}