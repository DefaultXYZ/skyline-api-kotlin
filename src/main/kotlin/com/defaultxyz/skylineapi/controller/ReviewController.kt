package com.defaultxyz.skylineapi.controller

import com.defaultxyz.skylineapi.repository.ReviewRepository
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(private val repository: ReviewRepository)