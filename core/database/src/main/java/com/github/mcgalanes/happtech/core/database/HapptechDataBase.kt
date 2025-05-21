package com.github.mcgalanes.happtech.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.mcgalanes.happtech.core.database.dao.ArtObjectLightDao
import com.github.mcgalanes.happtech.core.database.entity.ArtObjectLightEntity

internal const val DATABASE_FILENAME = "happtech.db"

@Database(
    entities = [ArtObjectLightEntity::class],
    version = 1,
)
abstract class HapptechDataBase : RoomDatabase() {
    abstract fun artObjectLightDao(): ArtObjectLightDao
}
