package com.defaultxyz.skylineapi.controller

import com.defaultxyz.skylineapi.model.Location
import com.defaultxyz.skylineapi.model.Review
import com.defaultxyz.skylineapi.model.User
import com.defaultxyz.skylineapi.model.request.AddLocationRequest
import com.defaultxyz.skylineapi.model.response.Response
import com.defaultxyz.skylineapi.repository.LocationRepository
import com.defaultxyz.skylineapi.repository.ReviewRepository
import com.defaultxyz.skylineapi.repository.UserRepository
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
class AppRestController(
        private val userRepository: UserRepository,
        private val locationRepository: LocationRepository,
        private val reviewRepository: ReviewRepository
) {

    @PostMapping("/user/register")
    fun registerUser(@RequestBody user: User): Response<User> {
        val existedUser = userRepository.findByEmail(user.email)
        existedUser?.let { return Response("User with email already exists") }
        userRepository.save(user).apply {
            return Response("User created", this)
        }
    }

    @GetMapping("/user/login")
    fun login(
            @Param("email") email: String,
            @Param("password") password: String
    ): Response<User> {
        val existedUser = userRepository.findByEmail(email) ?: return Response("User doesn't exists")
        return when (existedUser.password) {
            password -> Response("Login successful", existedUser)
            else -> Response("Password is incorrect", null)
        }
    }

    @GetMapping("/location/all")
    fun getAllLocations() = Response(
            "All location loaded",
            locationRepository.findAll().toList())

    @PostMapping("/location/new")
    fun addLocation(@RequestBody request: AddLocationRequest): Response<Location> = with(request) {
        locationRepository.findByName(location.name)?.let {
            return@with Response("Location with this name already exists")
        }
        return@with locationRepository.save(location).id?.let { locationId ->
            review.apply { this.locationId = locationId }.also { reviewRepository.save(it) }
            Response("Location added successfully", locationRepository.findById(locationId).get())
        } ?: Response("Error on adding location")
    }

    @PostMapping("/review/new")
    fun addReview(@RequestBody review: Review) = Response(
            "Review added successfully",
            reviewRepository.save(review)
    )
}