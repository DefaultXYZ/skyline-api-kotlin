package com.defaultxyz.skylineapi.model

import java.util.*
import javax.persistence.*

@Entity(name = "t_user")
data class User(
        @Id @GeneratedValue val id: Int,
        val email: String,
        val password: String,
        @Column(name = "first_name") val firstName: String,
        @Column(name = "last_name") val lastName: String,
        @Column(name = "date_of_birth") val dateOfBirth: Date
)