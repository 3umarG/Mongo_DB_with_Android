package com.example.mongodbproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mongodbproject.data.model.Person
import com.example.mongodbproject.databinding.ActivityMainBinding
import com.example.mongodbproject.ui.adapter.PersonAdapter
import com.example.mongodbproject.ui.viewmodel.MongodbViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.mongodb.kbson.ObjectId

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MongodbViewModel by viewModels()
    private lateinit var personAdapter: PersonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            viewModel.data.collect {
                loadDataToRecyclerView(it)
            }
        }
        binding.btnAdd.setOnClickListener {
            addPerson(binding.etName.text.toString())
        }

        binding.btnDelete.setOnClickListener {
            deletePerson(ObjectId(hexString = binding.etId.text.toString()))
        }

        binding.btnFilter.setOnClickListener {
            filterPerson(binding.etName.text.toString())
        }

        binding.btnUpdate.setOnClickListener {
            updatePerson(
                ObjectId(hexString = binding.etId.text.toString()),
                binding.etName.text.toString()
            )
        }


    }


    private fun loadDataToRecyclerView(data: List<Person>) {
        personAdapter = PersonAdapter(data,
            object : PersonAdapter.OnItemClickListener {
                override fun onItemClick(person: Person) {
                    binding.etId.setText(person.id.toHexString())
                }
            })
        binding.recyclerView.apply {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun updatePerson(id: ObjectId, name: String) {
        viewModel.updatePerson(id, name)
        viewModel.getData()
    }

    private fun filterPerson(name: String) {
        viewModel.filterData(name)
    }

    private fun deletePerson(id: ObjectId) {
        viewModel.deletePerson(id)
        viewModel.getData()
    }

    private fun addPerson(name: String) {
        viewModel.insertPerson(name)
    }
}