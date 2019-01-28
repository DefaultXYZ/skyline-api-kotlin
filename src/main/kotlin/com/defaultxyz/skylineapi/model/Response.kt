package com.defaultxyz.skylineapi.model

data class Response<T : Any>(
        val message: String,
        val data: T? = null
)