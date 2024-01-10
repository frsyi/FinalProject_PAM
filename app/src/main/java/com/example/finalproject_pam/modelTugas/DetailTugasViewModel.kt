package com.example.finalproject_pam.modelTugas

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoryTugas
import com.example.finalproject_pam.ui.halamanTugas.DetailTugasDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailTugasViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryTugas: RepositoryTugas
) : ViewModel() {

    private val tugasId: Int = checkNotNull(savedStateHandle[DetailTugasDestination.tugasIdArg])

    val uiTugasState: StateFlow<ItemDetailTugasUiState> = repositoryTugas.getTugasStream(tugasId)
        .filterNotNull()
        .map { ItemDetailTugasUiState(detailTugas = it.toDetailTugas()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailTugasUiState()
        )

    suspend fun deleteItem() {
        repositoryTugas.deleteTugas(uiTugasState.value.detailTugas.toTugas())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailTugasUiState(
    val outOfStock: Boolean = true,
    val detailTugas: DetailTugas = DetailTugas()
)