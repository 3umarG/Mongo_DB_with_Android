package com.example.mongodbproject.data.repository

import com.example.mongodbproject.data.model.Person
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface Repository {
    fun getData() : Flow<List<Person>>
    fun filterData(name :String) : Flow<List<Person>>

    suspend fun insertPerson(person: Person)
    suspend fun updatePerson(person: Person)
    suspend fun deletePerson(name: String)
}