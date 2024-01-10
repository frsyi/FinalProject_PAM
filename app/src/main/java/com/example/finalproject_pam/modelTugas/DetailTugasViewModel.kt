package com.example.finalproject_pam.modelTugas

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoryTugas
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class DetailTugasViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryTugas: RepositoryTugas
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailTugasUiState(
    val outOfStock: Boolean = true,
    val detailTugas: DetailTugas = DetailTugas()
)