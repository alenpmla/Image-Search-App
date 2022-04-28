package com.example.search_image.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SearchListingEntity::class],
    version = 1
)
abstract class SearchDatabase : RoomDatabase() {
    abstract val dao: SearchDao
}