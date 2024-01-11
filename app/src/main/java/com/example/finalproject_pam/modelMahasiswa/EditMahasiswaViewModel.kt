package com.example.finalproject_pam.modelMahasiswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoryMahasiswa
import com.example.finalproject_pam.ui.halamanMahasiswa.ItemEditMahasiswaDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditMahasiswaViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMahasiswa: RepositoryMahasiswa
) : ViewModel() {

    var mahasiswaUiState by mutableStateOf(UIStateMahasiswa())
        private set

    private val mahasiswaId: Int = checkNotNull(savedStateHandle[ItemEditMahasiswaDestination.itemIdArg])

    init {
        viewModelScope.launch {
            mahasiswaUiState = repositoryMahasiswa.getMahasiswaStream(mahasiswaId)
                .filterNotNull()
                .first()
                .toUiStateMahasiswa(true)
        }
    }

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