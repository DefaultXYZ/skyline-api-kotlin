package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository: JpaRepository<Review, Int>