package com.github.mcgalanes.happtech.core.domain.repository

import com.github.mcgalanes.happtech.core.domain.model.LeMondeElement

interface LeMondeRepository {
    suspend fun getElements(): Result<List<LeMondeElement>>
}
