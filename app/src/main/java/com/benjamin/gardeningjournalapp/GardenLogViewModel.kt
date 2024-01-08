// GardenLogViewModel.kt
package com.benjamin.gardeningjournalapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GardenLogViewModel(private val repository: PlantRepository) : ViewModel() {
    val allPlants: LiveData<List<Plant>> = repository.allPlants

    fun insertPlant(plant: Plant) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(plant)
        }
    }
}


