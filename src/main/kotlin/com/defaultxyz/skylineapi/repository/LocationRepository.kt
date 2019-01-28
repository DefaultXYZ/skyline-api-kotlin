package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.Location
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository : JpaRepository<Location, Int> {

    fun findByCreatorEmail(email: String): List<Location>
}