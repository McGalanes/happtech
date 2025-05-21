package com.github.mcgalanes.happtech.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.mcgalanes.happtech.core.database.entity.ArtObjectLightEntity
import com.github.mcgalanes.happtech.core.database.entity.ArtObjectLightTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtObjectLightDao {
    @Query("SELECT * FROM ${ArtObjectLightTable.TABLE_NAME}")
    fun getAll(): Flow<List<ArtObjectLightEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<ArtObjectLightEntity>)

    @Query("DELETE FROM ${ArtObjectLightTable.TABLE_NAME}")
    suspend fun clear()
}
