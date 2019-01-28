package com.defaultxyz.skylineapi.controller

import com.defaultxyz.skylineapi.model.Location
import com.defaultxyz.skylineapi.repository.LocationRepository
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationController(val repository: LocationRepository) {

    @GetMapping("/location/user")
    fun findByEmail(@Param("email") email: String): List<Location> = repository.findByCreatorEmail(email)
}