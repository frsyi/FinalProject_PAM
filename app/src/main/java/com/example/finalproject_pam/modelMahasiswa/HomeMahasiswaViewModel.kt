package com.example.finalproject_pam.modelMahasiswa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.data.Mahasiswa
import com.example.finalproject_pam.repository.RepositoryMahasiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeMahasiswaViewModel(private val repositoryMahasiswa: RepositoryMahasiswa): ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    val homeUiState: StateFlow<HomeMahasiswaUiState> = repositoryMahasiswa.getAllMahasiswaStream()
        .filterNotNull()
        .map { HomeMahasiswaUiState(listMahasiswa = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeMahasiswaUiState()
        )
    data class HomeMahasiswaUiState(
        val listMahasiswa: List<Mahasiswa> = listOf()
    )
}