package com.example.finalproject_pam.modelMahasiswa

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoryMahasiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsMahasiswaViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMahasiswa: RepositoryMahasiswa
) : ViewModel() {

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

}


data class ItemDetailsMahasiswaUiState(
    val outOfStock: Boolean = true,
    val detailMahasiswa: DetailMahasiswa = DetailMahasiswa()
)