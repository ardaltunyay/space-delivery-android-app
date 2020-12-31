package com.github.spacedelivery.androidapp.data.local.dao

import androidx.room.*
import com.github.spacedelivery.androidapp.data.local.entity.SpaceVehicleEntity

@Dao
interface ISpaceVehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spaceVehicle: SpaceVehicleEntity)

    @Update
    suspend fun update(spaceVehicle: SpaceVehicleEntity)

    @Delete
    suspend fun delete(spaceVehicle: SpaceVehicleEntity)

    @Query("DELETE FROM space_vehicle")
    suspend fun clear()

    @Query("SELECT * from space_vehicle WHERE id = :key")
    suspend fun get(key: Int): SpaceVehicleEntity?

}