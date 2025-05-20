package com.github.mcgalanes.happtech.core.network

data class ApiException(
    val code: Int,
    val description: String,
) : Exception()
