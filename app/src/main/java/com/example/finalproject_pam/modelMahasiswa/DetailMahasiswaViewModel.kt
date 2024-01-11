package com.example.finalproject_pam.modelMahasiswa

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoryMahasiswa
import com.example.finalproject_pam.ui.halamanMahasiswa.DetailMahasiswaDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsMahasiswaViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMahasiswa: RepositoryMahasiswa
) : ViewModel() {
    private val mahasiswaId: Int = checkNotNull(savedStateHandle[DetailMahasiswaDestination.mahasiswaIdArg])

    val uiState: StateFlow<ItemDetailsMahasiswaUiState> = repositoryMahasiswa.getMahasiswaStream(mahasiswaId)
        .filterNotNull()
        .map {
            ItemDetailsMahasiswaUiState(detailMahasiswa = it.toDetailMahasiswa())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailsMahasiswaUiState()
        )

    suspend fun deleteItem() {
        repositoryMahasiswa.deleteMahasiswa(uiState.value.detailMahasiswa.toMahasiswa())
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class ItemDetailsMahasiswaUiState(
    val outOfStock: Boolean = true,
    val detailMahasiswa: DetailMahasiswa = DetailMahasiswa()
)