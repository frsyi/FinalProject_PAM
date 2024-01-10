package com.example.finalproject_pam.modelTugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoryTugas
import com.example.finalproject_pam.ui.halamanTugas.EditTugasDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditTugasViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryTugas: RepositoryTugas
) : ViewModel() {
    var tugasUiState by mutableStateOf(UIStateTugas())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[EditTugasDestination.itemIdArg])

    init {
        viewModelScope.launch {
            tugasUiState = repositoryTugas.getTugasStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateTugas(true)
        }
    }

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