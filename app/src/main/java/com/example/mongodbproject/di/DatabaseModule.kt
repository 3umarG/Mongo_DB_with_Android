package com.example.mongodbproject.di

import com.example.mongodbproject.data.model.Address
import com.example.mongodbproject.data.model.Person
import com.example.mongodbproject.data.model.Pets
import com.example.mongodbproject.data.repository.MongoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Provide Mongodb
    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Person::class, Address::class, Pets::class
            )
        ).compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    // Provide My Repository
    @Provides
    @Singleton
    fun provideMongoRepository(realm: Realm) : MongoRepository {
        return MongoRepository(realm)
    }

}