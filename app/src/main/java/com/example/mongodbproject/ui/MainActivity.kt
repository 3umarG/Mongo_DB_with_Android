package com.example.mongodbproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mongodbproject.databinding.ActivityMainBinding
import com.example.mongodbproject.ui.viewmodel.MongodbViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MongodbViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            addPerson(binding.etName.text.toString())
        }

        binding.btnDelete.setOnClickListener {
            deletePerson(binding.etName.text.toString())
        }

        binding.btnFilter.setOnClickListener {
            filterPerson(binding.etName.text.toString())
        }

        binding.btnUpdate.setOnClickListener {
            updatePerson(binding.etId.text.toString() , binding.etName.text.toString())
        }


    }

    private fun updatePerson(id : String , name : String) {

    }

    private fun filterPerson(name: String) {

    }

    private fun deletePerson(name: String) {

    }

    private fun addPerson(name: String) {

    }
}