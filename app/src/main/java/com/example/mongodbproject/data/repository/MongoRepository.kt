package com.example.mongodbproject.data.repository

import com.example.mongodbproject.data.model.Person
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MongoRepository(private val realm: Realm) : Repository {

    override fun getData(): Flow<List<Person>> {
        return realm.query<Person>().asFlow().map {
            it.list
        }
    }

    override fun filterData(name: String): Flow<List<Person>> {
        return realm.query<Person>(
            query = "name CONTAINS[c] $0", name
        ).asFlow().map {
            it.list
        }
    }

    override suspend fun insertPerson(person: Person) {
        realm.write {
            copyToRealm(person)
        }
    }

    override suspend fun updatePerson(person: Person) {
        realm.write {
            val queriedPerson = realm.query<Person>(
                query = "id == $0", person.id
            ).first()
                .find()

            queriedPerson?.name = person.name
        }
    }

    override suspend fun deletePerson(name: String) {
        realm.write {
            val queriedPerson = realm.query<Person>(
                query = "name == $0", name
            ).first().find()

            try {
                queriedPerson?.let {
                    delete(it)
                }
            } catch (ex: Exception) {
                println("MONGODB REPOSITORY :: ${ex.message}")
            }
        }
    }

}