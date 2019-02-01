package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.LocationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository : JpaRepository<LocationEntity, Int> {

    fun findByNameIgnoreCase(name: String): LocationEntity?
}