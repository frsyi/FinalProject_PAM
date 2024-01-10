package com.example.finalproject_pam.modelTugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoryTugas
import kotlinx.coroutines.launch

class EditTugasViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryTugas: RepositoryTugas
) : ViewModel() {
    var tugasUiState by mutableStateOf(UIStateTugas())
        private set

    suspend fun updateTugas() {
        if (validasiInput(tugasUiState.detailTugas)) {
            repositoryTugas.updateTugas(tugasUiState.detailTugas.toTugas())
        } else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailTugas: DetailTugas) {
        tugasUiState = UIStateTugas(detailTugas = detailTugas, isEntryValid = validasiInput(detailTugas))
    }

    private fun validasiInput(uiState: DetailTugas = tugasUiState.detailTugas): Boolean {
        return with(uiState) {
            namaTugas.isNotBlank() && detailTugas.isNotBlank() && deadline.isNotBlank()
        }
    }
}