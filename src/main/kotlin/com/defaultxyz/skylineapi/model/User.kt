package com.defaultxyz.skylineapi.model

import javax.persistence.*

@Entity(name = "t_user")
data class UserEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
        val email: String,
        val password: String,
        @Column(name = "first_name") val firstName: String,
        @Column(name = "last_name") val lastName: String,
        /**
         * Format YYYY-MM-DD
         */
        @Column(name = "date_of_birth") val dateOfBirth: String
)

data class UserModel(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        /**
         * Format YYYY-MM-DD
         */
        val dateOfBirth: String
)

fun UserModel.toEntity() = UserEntity(
        email = email,
        password = password,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth
)

fun UserEntity.toModel() = UserModel(email, password, firstName, lastName, dateOfBirth)