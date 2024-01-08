package com.benjamin.gardeningjournalapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface PlantDao {
    @Query("SELECT * FROM plants")
    fun getAllPlants(): LiveData<List<Plant>>

    @Insert
    suspend fun insert(plant: Plant)

    @Update
    suspend fun update(plant: Plant)

    @Delete
    suspend fun delete(plant: Plant)

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlantById(plantId: Int): LiveData<Plant>
}
