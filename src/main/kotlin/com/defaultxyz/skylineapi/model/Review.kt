package com.defaultxyz.skylineapi.model

import javax.persistence.*

@Entity(name = "t_review")
data class Review(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int?,
        val text: String,
        val rating: Int,
        @Column(name = "location_id") var locationId: Int?,

        @ManyToOne
        @JoinColumn(name = "user_id")
        var user: User? = null
)