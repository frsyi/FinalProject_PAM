package com.example.finalproject_pam.modelTugas

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.data.Tugas
import com.example.finalproject_pam.repository.RepositoryTugas
import com.example.finalproject_pam.ui.halamanMahasiswa.DetailMahasiswaDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeTugasViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryTugas: RepositoryTugas
) : ViewModel() {

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val mahasiswaId: Int = checkNotNull(savedStateHandle[DetailMahasiswaDestination.mahasiswaIdArg])

    val homeTugasUiState: StateFlow<HomeTugasUiState> = repositoryTugas.getAllTugasStream(mahasiswaId)
        .filterNotNull()
        .map { HomeTugasUiState(listTugas = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeTugasUiState()
        )

    data class HomeTugasUiState(
        val listTugas: List<Tugas> = listOf()
    )
}