package com.example.character

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: PersonRepository
    val allPeople: LiveData<List<Person>>

    init {
        val dao = PersonDatabase.getDatabase(application).personDao()
        repo = PersonRepository(dao)
        allPeople = repo.allPeople
    }

    fun insert(person: Person) = viewModelScope.launch {
        repo.insert(person)
    }
}
