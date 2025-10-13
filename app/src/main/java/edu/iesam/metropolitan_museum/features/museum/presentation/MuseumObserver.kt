package edu.iesam.metropolitan_museum.features.museum.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

class MuseumObserver {

    companion object {
        private val _selectedArt = MutableLiveData<WorkOfArt?>()
        val selectedArt: LiveData<WorkOfArt?> get() = _selectedArt

        fun setWorkOfArt(workOfArt: WorkOfArt) {
            _selectedArt.value = workOfArt
        }

        fun clear() {
            _selectedArt.value = null
        }
    }
}