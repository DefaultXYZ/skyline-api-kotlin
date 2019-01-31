package com.defaultxyz.skylineapi.model.response

data class Response<T : Any>(
        val message: String,
        val data: T? = null
)