package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Int> {

    fun findByEmailIgnoreCase(email: String): UserEntity?
}