package com.defaultxyz.skylineapi.controller

import com.defaultxyz.skylineapi.model.Location
import com.defaultxyz.skylineapi.repository.LocationRepository
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationController(val repository: LocationRepository) {

    @GetMapping("/location/all")
    fun getAllLocations(): List<Location> = repository.findAll()

    @GetMapping("/location/user")
    fun getLocationByEmail(@Param("email") email: String): List<Location> = repository.findByCreatorEmail(email)
}