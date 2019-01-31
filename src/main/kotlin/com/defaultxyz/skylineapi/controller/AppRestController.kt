package com.defaultxyz.skylineapi.controller

import com.defaultxyz.skylineapi.model.Location
import com.defaultxyz.skylineapi.model.User
import com.defaultxyz.skylineapi.model.response.Response
import com.defaultxyz.skylineapi.repository.LocationRepository
import com.defaultxyz.skylineapi.repository.ReviewRepository
import com.defaultxyz.skylineapi.repository.UserRepository
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppRestController(
        private val userRepository: UserRepository,
        private val locationRepository: LocationRepository,
        private val reviewController: ReviewRepository
) {

    @PostMapping("/user/register")
    fun registerUser(user: User): Response<User> {
        val existedUser = userRepository.findByEmail(user.email)
        existedUser?.let { return Response("User already exists") }
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

    @PostMapping("/location/new")
    fun addLocation() {
        // TODO: Implement
    }

    @GetMapping("/location/all")
    fun getAllLocations(): List<Location> = locationRepository.findAll().toList()

    @GetMapping("/location/user")
    fun getLocationByEmail(@Param("email") email: String): List<Location> =
            locationRepository.findByCreatorEmail(email)

    @PostMapping("/review/new")
    fun addReview() {
        // TODO: Implement
    }
}