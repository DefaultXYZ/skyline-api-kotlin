package com.defaultxyz.skylineapi.controller

import com.defaultxyz.skylineapi.extensions.takeNonEmpty
import com.defaultxyz.skylineapi.model.*
import com.defaultxyz.skylineapi.model.response.Response
import com.defaultxyz.skylineapi.repository.LocationRepository
import com.defaultxyz.skylineapi.repository.ReviewRepository
import com.defaultxyz.skylineapi.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AppRestController(
        private val userRepository: UserRepository,
        private val locationRepository: LocationRepository,
        private val reviewRepository: ReviewRepository
) {

    @PostMapping("/user/register")
    fun registerUser(@RequestBody user: UserModel) =
            when (userRepository.findByEmailIgnoreCase(user.email)) {
                null -> Response("User created", userRepository.save(user.toEntity()).toModel())
                else -> Response("User with email already exists")
            }

    @GetMapping("/user/login")
    fun login(email: String, password: String) =
            userRepository.findByEmailIgnoreCase(email)?.toModel()?.let { user ->
                when (user.password) {
                    password -> Response("Login successful", user)
                    else -> Response("Password is incorrect")
                }
            } ?: Response("User doesn't exists")

    @GetMapping("/location/all")
    fun getAllLocations() = Response(
            "Locations loaded successfully",
            locationRepository.findAll().map(LocationEntity::toModel)
    )

    @PostMapping("/location/new")
    fun addLocation(
            email: String,
            @RequestBody request: NewLocationModel
    ) = when (locationRepository.findByNameIgnoreCase(request.location.name)) {
        null -> {
            request.location.toEntityOrNull(email)
                    ?.apply { locationRepository.save(this) }
                    ?.let { location -> location.userId to location.id }
                    ?.takeNonEmpty()
                    ?.let { (userId, locationId) -> request.review.toEntity(userId, locationId) }
                    ?.apply { reviewRepository.save(this) }
                    ?.locationId
                    ?.let { locationRepository.findByIdOrNull(it) }
                    ?.let { Response("Location added successfully", it) } ?: Response("Error on adding location")
        }
        else -> Response("Location with this name already exists")
    }


    @GetMapping("/review/all")
    fun getAllReviewsByLocationName(name: String) =
            locationRepository.findByNameIgnoreCase(name)
                    ?.let { location -> userRepository.findByIdOrNull(location.userId) to location }
                    ?.takeNonEmpty()
                    ?.let { (user, location) ->
                        location.id?.let { reviewRepository.findAllByLocationId(it) }
                                ?.map { it.toModel(user.email, location.name) }
                    }?.let { Response("Reviews loaded successfully", it) }
                    ?: Response("Error on load reviews", emptyList())

    @PostMapping("/review/new")
    fun addReview(@RequestBody review: ReviewModel) = review.toEntityOrNull()
            ?.let { entity -> reviewRepository.save(entity) }?.toModelOrNull()
            ?.let { Response("Review added successfully", it) }
            ?: Response("Error on adding review")

    private fun LocationModel.toEntityOrNull(email: String) =
            userRepository.findByEmailIgnoreCase(email)?.id?.let { userId -> toEntity(userId) }

    private fun ReviewModel.toEntityOrNull() =
            (userRepository.findByEmailIgnoreCase(userEmail) to locationRepository.findByNameIgnoreCase(locationName))
                    .takeNonEmpty()
                    ?.let { (user, location) -> user.id to location.id }
                    ?.takeNonEmpty()
                    ?.let { (userId, locationId) -> toEntity(userId, locationId) }

    private fun ReviewEntity.toModelOrNull() =
            (userRepository.findByIdOrNull(userId) to locationRepository.findByIdOrNull(locationId))
                    .takeNonEmpty()
                    ?.let { (user, location) -> toModel(user.email, location.name) }

}