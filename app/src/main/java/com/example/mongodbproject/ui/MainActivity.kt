package com.example.mongodbproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mongodbproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
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