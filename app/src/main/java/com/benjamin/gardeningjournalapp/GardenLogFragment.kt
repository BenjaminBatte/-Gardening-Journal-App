// GardenLogFragment.kt
package com.benjamin.gardeningjournalapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.benjamin.gardeningjournalapp.databinding.FragmentGardenLogBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GardenLogFragment : Fragment() {

    private lateinit var binding: FragmentGardenLogBinding
    private lateinit var gardenLogViewModel: GardenLogViewModel
    private lateinit var plantAdapter: PlantAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gardenLogViewModel = ViewModelProvider(this).get(GardenLogViewModel::class.java)
        plantAdapter = PlantAdapter()

        binding.recyclerViewPlants.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = plantAdapter
        }

        gardenLogViewModel.allPlants.observe(viewLifecycleOwner, { plants ->
            plantAdapter.submitList(plants)
        })

        binding.btnAddPlant.setOnClickListener {
            // Handle the click event to add a new plant
            // You may show a dialog or navigate to a new screen for plant details input
            // Then call the function to insert the plant into the database
            addPlantToDatabase()
        }
    }

    private fun addPlantToDatabase() {
        // Implement the logic to add a new plant to the database
        // For simplicity, you can use a coroutine to insert data asynchronously
        GlobalScope.launch(Dispatchers.IO) {
            val newPlant = Plant(
                name = "New Plant",
                type = "Type",
                wateringFrequency = 7,
                plantingDate = "2024-01-07"
            )
            gardenLogViewModel.insertPlant(newPlant)
        }
    }
}
