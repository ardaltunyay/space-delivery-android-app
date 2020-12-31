package com.github.spacedelivery.androidapp.data.local.dao

import androidx.room.*
import com.github.spacedelivery.androidapp.data.local.entity.SpaceStationEntity

@Dao
interface ISpaceStationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spaceStationEntity: SpaceStationEntity)

    @Update
    suspend fun update(spaceStationEntity: SpaceStationEntity)

    @Delete
    suspend fun delete(spaceStationEntity: SpaceStationEntity)

    @Query("DELETE FROM space_station")
    suspend fun clear()

    @Query("SELECT * from space_station WHERE name = :key")
    suspend fun get(key: String): SpaceStationEntity?

}