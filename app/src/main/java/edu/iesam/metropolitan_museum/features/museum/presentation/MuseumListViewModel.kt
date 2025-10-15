package edu.iesam.metropolitan_museum.features.museum.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.metropolitan_museum.features.museum.domain.ErrorApp
import edu.iesam.metropolitan_museum.features.museum.domain.MuseumGetUseCase
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
            val result = getAllWorkOfArtUseCase.fetch()

            result.fold(
                onSuccess = { workOfArtList ->
                    _workOfArtList.postValue(workOfArtList.map { it.toUiModel() })
                    _error.postValue(null)
                },
                onFailure = { err ->
                    _error.postValue(ErrorApp.ServerErrorApp)
                    _workOfArtList.postValue(emptyList())
                }
            )
        }
    }
}
