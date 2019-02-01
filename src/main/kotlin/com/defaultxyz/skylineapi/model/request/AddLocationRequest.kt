package com.defaultxyz.skylineapi.model.request

import com.defaultxyz.skylineapi.model.Location
import com.defaultxyz.skylineapi.model.Review
import com.defaultxyz.skylineapi.model.User

data class AddLocationRequest(
        val location: Location,
        val review: Review,
        val user: User
) {
    init {
        location.creator = user
        review.user = user
    }
}