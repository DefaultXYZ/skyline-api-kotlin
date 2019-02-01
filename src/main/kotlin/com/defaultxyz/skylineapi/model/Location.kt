package com.defaultxyz.skylineapi.model

import javax.persistence.*

@Entity(name = "t_location")
data class LocationEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
        val name: String,
        val latitude: Double,
        val longitude: Double,
        @Column(name = "user_id") val userId: Int
)

data class LocationModel(
        val name: String,
        val latitude: Double,
        val longitude: Double
)

data class NewLocationModel(
        val location: LocationModel,
        val review: SimpleReviewModel
)

fun LocationEntity.toModel() = LocationModel(name, latitude, longitude)

fun LocationModel.toEntity(userId: Int) = LocationEntity(
        name = name,
        latitude = latitude,
        longitude = longitude,
        userId = userId
)