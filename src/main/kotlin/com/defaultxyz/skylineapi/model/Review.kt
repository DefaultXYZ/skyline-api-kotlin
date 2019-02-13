package com.defaultxyz.skylineapi.model

import javax.persistence.*

@Entity(name = "t_review")
data class ReviewEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
        val text: String,
        val rating: Int,
        @Column(name = "location_id") val locationId: Int,
        @Column(name = "user_id") val userId: Int
)

data class SimpleReviewModel(
        val text: String,
        val rating: Int
)

data class ReviewModel(
        val text: String,
        val rating: Int,
        val locationName: String,
        val userName: String
)

fun ReviewModel.toEntity(userId: Int, locationId: Int) = ReviewEntity(
        text = text,
        rating = rating,
        locationId = locationId,
        userId = userId
)

fun SimpleReviewModel.toEntity(userId: Int, locationId: Int) = ReviewEntity(
        text = text,
        rating = rating,
        locationId = locationId,
        userId = userId
)


fun ReviewEntity.toModel(userName: String, locationName: String) = ReviewModel(
        text, rating, locationName, userName
)