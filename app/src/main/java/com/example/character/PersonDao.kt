package com.example.character

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    suspend fun insert(person: Person)

    @Query("SELECT * FROM person_table ORDER BY id DESC")
    fun getAllPeople(): LiveData<List<Person>>
}