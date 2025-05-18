package com.example.character

class PersonRepository(private val dao: PersonDao) {
    val allPeople = dao.getAllPeople()
    suspend fun insert(person: Person) = dao.insert(person)
}
