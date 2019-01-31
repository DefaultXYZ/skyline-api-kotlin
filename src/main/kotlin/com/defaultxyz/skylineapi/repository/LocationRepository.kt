package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.Location
import org.springframework.data.repository.CrudRepository

interface LocationRepository : CrudRepository<Location, Int> {

    fun findByCreatorEmail(email: String): List<Location>
}