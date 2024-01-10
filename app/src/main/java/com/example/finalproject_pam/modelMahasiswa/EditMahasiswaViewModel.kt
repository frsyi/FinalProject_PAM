package com.example.finalproject_pam.modelMahasiswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoryMahasiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditMahasiswaViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMahasiswa: RepositoryMahasiswa
) : ViewModel() {

    var mahasiswaUiState by mutableStateOf(UIStateMahasiswa())
        private set

    suspend fun updateMahasiswa() {
        if (validasiInput(mahasiswaUiState.detailMahasiswa)) {
            repositoryMahasiswa.updateMahasiswa(mahasiswaUiState.detailMahasiswa.toMahasiswa())
        } else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailMahasiswa: DetailMahasiswa) {
        mahasiswaUiState = UIStateMahasiswa(
            detailMahasiswa = detailMahasiswa,
            isEntryValid = validasiInput(detailMahasiswa)
        )
    }

    private fun validasiInput(uiState: DetailMahasiswa = mahasiswaUiState.detailMahasiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && nim.isNotBlank() && semester.isNotBlank()
        }
    }
}