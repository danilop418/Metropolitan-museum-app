package edu.iesam.metropolitan_museum.features.museum.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

class WorkOfArtDetailViewModel : ViewModel() {

    private val _workOfArt = MutableLiveData<WorkOfArt>()
    val workOfArt: LiveData<WorkOfArt> = _workOfArt

    fun setWorkOfArt(artwork: WorkOfArt?) {
        artwork?.let { _workOfArt.value = it }
    }
}