package com.defaultxyz.skylineapi.model

import javax.persistence.*

@Entity(name = "t_review")
data class Review(
        @Id @GeneratedValue val id: Int,
        val text: String,
        val rating: Int,

        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User
)