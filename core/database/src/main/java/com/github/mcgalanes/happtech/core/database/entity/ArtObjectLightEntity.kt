package com.github.mcgalanes.happtech.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal object ArtObjectLightTable {
    const val TABLE_NAME = "art_object_light"
    const val COLUMN_OBJECT_NUMBER = "objectNumber"
    const val COLUMN_TITLE = "title"
    const val COLUMN_HAS_IMAGE = "hasImage"
    const val COLUMN_IMAGE_URL = "imageUrl"
    const val COLUMN_IMAGE_RATIO = "imageRatio"
    const val COLUMN_QUERY = "query"
}

@Entity(tableName = ArtObjectLightTable.TABLE_NAME)
data class ArtObjectLightEntity(
    @PrimaryKey
    @ColumnInfo(name = ArtObjectLightTable.COLUMN_OBJECT_NUMBER)
    val objectNumber: String,

    @ColumnInfo(name = ArtObjectLightTable.COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = ArtObjectLightTable.COLUMN_HAS_IMAGE)
    val hasImage: Boolean,

    @ColumnInfo(name = ArtObjectLightTable.COLUMN_IMAGE_URL)
    val imageUrl: String?,

    @ColumnInfo(name = ArtObjectLightTable.COLUMN_IMAGE_RATIO)
    val imageRatio: Float?,

    @ColumnInfo(name = ArtObjectLightTable.COLUMN_QUERY)
    val query: String,
)
