package com.example.mongodbproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mongodbproject.data.model.Person
import com.example.mongodbproject.data.repository.MongoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class MongodbViewModel @Inject constructor(
    private val repo: MongoRepository
) : ViewModel() {

    private var mutableData: MutableStateFlow<List<Person>> = MutableStateFlow(listOf())
    val data: StateFlow<List<Person>> = mutableData

    init {
        getData()
    }

     fun getData() {
        viewModelScope.launch {
            repo.getData().collect {
                mutableData.value = it
            }
        }
    }

    fun insertPerson(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            name.isNotEmpty().let {
                repo.insertPerson(Person().apply {
                    this.name = name
                })
            }
            getData()
        }
    }


    fun updatePerson(id: ObjectId, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            (name.isNotEmpty() && id.toHexString().isNotEmpty()).let {
                val person = Person().apply {
                    this.id = id
                    this.name = name
                }
                repo.updatePerson(person)
            }
//            getData()
        }
    }


    fun deletePerson(id: ObjectId) {
        viewModelScope.launch(Dispatchers.IO) {
            id.toHexString().isNotEmpty().let {
                repo.deletePerson(id)
            }
//            getData()
        }
    }

    fun filterData(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            name.isNotEmpty().let {
                repo.filterData(name).collect {
                    this@MongodbViewModel.mutableData.value = it
                }
            }
        }
    }

}