package com.defaultxyz.skylineapi.repository

import com.defaultxyz.skylineapi.model.Review
import org.springframework.data.repository.CrudRepository

interface ReviewRepository : CrudRepository<Review, Int> {

}