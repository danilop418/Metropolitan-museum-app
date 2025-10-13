package edu.iesam.metropolitan_museum.features.museum.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.metropolitan_museum.features.museum.domain.ErrorApp
import edu.iesam.metropolitan_museum.features.museum.domain.MuseumGetUseCase
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt
import kotlinx.coroutines.launch

class MuseumListViewModel(
    private val getAllWorkOfArtUseCase: MuseumGetUseCase
) : ViewModel() {

    private val _workOfArtList = MutableLiveData<List<WorkOfArtUiModel>>()
    val workOfArtList: LiveData<List<WorkOfArtUiModel>> = _workOfArtList

    private val _error = MutableLiveData<ErrorApp?>()
    val error: LiveData<ErrorApp?> = _error

    fun loadWorkOfArt() {
        viewModelScope.launch {
            val result = getAllWorkOfArtUseCase.invoke()

            result.fold(
                onSuccess = { workOfArtList ->
                    _workOfArtList.postValue(workOfArtList.map { it.toUiModel() })
                    _error.postValue(null)
                },
                onFailure = { err ->
                    val error = when (err) {
                        is ErrorApp.InternetConexionError -> ErrorApp.InternetConexionError
                        is ErrorApp.ServerErrorApp -> ErrorApp.ServerErrorApp
                        else -> ErrorApp.ServerErrorApp
                    }
                    _error.postValue(error)
                    _workOfArtList.postValue(emptyList())
                }
            )
        }
    }

    private fun WorkOfArt.toUiModel() = WorkOfArtUiModel(
        id = id,
        objectID = objectID,
        title = title,
        artistDisplayName = artistDisplayName,
        artistNationality = artistNationality,
        objectDate = objectDate,
        medium = medium,
        dimensions = dimensions,
        image = primaryImage
    )
}