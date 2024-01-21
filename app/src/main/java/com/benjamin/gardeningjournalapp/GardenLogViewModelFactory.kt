package com.benjamin.gardeningjournalapp
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benjamin.gardeningjournalapp.GardenLogViewModel
import com.benjamin.gardeningjournalapp.PlantDatabase
import com.benjamin.gardeningjournalapp.PlantRepository

class GardenLogViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GardenLogViewModel::class.java)) {
            val plantDao = PlantDatabase.getDatabase(application.applicationContext).plantDao()
            val repository = PlantRepository(plantDao)

            @Suppress("UNCHECKED_CAST")
            return GardenLogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
