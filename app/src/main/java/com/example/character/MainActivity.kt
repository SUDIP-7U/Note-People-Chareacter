package com.example.character

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PersonViewModel
    private lateinit var adapter: PersonAdapter

    private val ratingOptions = listOf("Very Good", "Good", "Moderate", "Bad", "Very Bad")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val spinner = findViewById<Spinner>(R.id.spinnerRating)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = PersonAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Spinner setup
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ratingOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        // ViewModel
        viewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        viewModel.allPeople.observe(this) {
            adapter.updateList(it)
        }

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val rating = spinner.selectedItem.toString()

            if (name.isNotBlank()) {
                viewModel.insert(Person(name = name, rating = rating))
                etName.text.clear()
            }
        }
    }
}
