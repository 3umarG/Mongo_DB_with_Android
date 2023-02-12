package com.example.mongodbproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mongodbproject.databinding.ActivityMainBinding
import com.example.mongodbproject.ui.viewmodel.MongodbViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : MongodbViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            addPerson()
        }

        binding.btnDelete.setOnClickListener {
            deletePerson()
        }

        binding.btnFilter.setOnClickListener {
            filterPerson()
        }

        binding.btnUpdate.setOnClickListener {
            updatePerson()
        }


    }

    private fun updatePerson() {

    }

    private fun filterPerson() {

    }

    private fun deletePerson() {

    }

    private fun addPerson() {

    }
}