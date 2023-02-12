package com.example.mongodbproject.data.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/***
 * To Make Model Class should be original class not data
 * and inherit from RealmObject
 *
 * [@PrimaryKey] --> tell the collection is that the primary key and invoke() generate it automatically
 * [@Index] --> help the DB on Indexing to fast the query operation if you use this field in Query.
 * [@Ignore] --> this field will not be part of model schema.
 * if we mark a field as Null the Realm Object know that is Optional
 * RealmInstant ==> For Store Date and Time .
 ***/
class Person : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId.invoke()

    @Index
    var name: String = ""
    var age: Int = 20

    @Ignore
    var adult: Boolean = age >= 20

    // One - to - One Relation
    // One Person --> One Address
    var address: Address? = null

    // One - to - Many Relation
    // Person - to Many Pets
    var pets: RealmList<Pets> = realmListOf()

}

class Address : RealmObject {
    var streetNum: Int = 0
    var streetName: String = ""
}

class Pets : RealmObject {
    var type: String = ""
}