package com.defaultxyz.skylineapi.model

import javax.persistence.*

@Entity(name = "t_location")
data class Location(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int?,
        val name: String,
        val latitude: Double,
        val longitude: Double,

        @ManyToOne
        @JoinColumn(name = "user_id")
        var creator: User? = null,

        @OneToMany
        @JoinColumn(name = "location_id")
        val reviews: List<Review>? = null
)