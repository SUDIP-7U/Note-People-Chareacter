package com.example.character

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile private var instance: PersonDatabase? = null

        fun getDatabase(context: Context): PersonDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDatabase::class.java,
                    "person_database"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}
