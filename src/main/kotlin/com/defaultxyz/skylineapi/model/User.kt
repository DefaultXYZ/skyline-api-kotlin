package com.defaultxyz.skylineapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "t_user")
data class User(
        @Id @GeneratedValue val id: Int?,
        val email: String,
        val password: String,
        @Column(name = "first_name") val firstName: String,
        @Column(name = "last_name") val lastName: String,
        @Column(name = "date_of_birth") val dateOfBirth: String
)