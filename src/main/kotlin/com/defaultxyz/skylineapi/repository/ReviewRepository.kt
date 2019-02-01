package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.ReviewEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<ReviewEntity, Int> {

    fun findAllByLocationId(locationId: Int): List<ReviewEntity>

}