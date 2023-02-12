package com.example.mongodbproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mongodbproject.data.model.Person
import com.example.mongodbproject.databinding.PersonItemLayoutBinding

class PersonAdapter(private val data: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(private val binding: PersonItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(person: Person) {
            binding.tvId.text = person.id.toString()
            binding.tvName.text = person.name
        }

        companion object {

            fun create(view: ViewGroup): PersonViewHolder {
                val inflater = LayoutInflater.from(view.context)
                val binding = PersonItemLayoutBinding.inflate(inflater, view, false)
                return PersonViewHolder(binding)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(data[position])
    }
}