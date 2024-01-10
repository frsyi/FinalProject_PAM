package com.example.finalproject_pam.modelTugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finalproject_pam.data.Tugas
import com.example.finalproject_pam.repository.RepositoryTugas

class EntryTugasViewModel(private val repositoryTugas: RepositoryTugas) : ViewModel() {
    var uiStateTugas by mutableStateOf(UIStateTugas())
        private set

    private fun validasiInput(uiState: DetailTugas = uiStateTugas.detailTugas): Boolean {
        return with(uiState) {
            namaTugas.isNotBlank() && detailTugas.isNotBlank() && deadline.isNotBlank()
        }
    }

    fun updateUiState(detailTugas: DetailTugas) {
        uiStateTugas = UIStateTugas(detailTugas = detailTugas, isEntryValid = validasiInput(detailTugas))
    }

    suspend fun saveTugas() {
        if (validasiInput()) {
            repositoryTugas.insertTugas(uiStateTugas.detailTugas.toTugas())
        }
    }
}

data class UIStateTugas(
    val detailTugas: DetailTugas = DetailTugas(),
    val isEntryValid: Boolean = false
)

data class DetailTugas(
    val id: Int = 0,
    val mahasiswaId: Int = 0,
    val namaTugas: String = "",
    val detailTugas: String = "",
    val deadline: String = ""
)

fun DetailTugas.toTugas(): Tugas = Tugas(
    id = id,
    mahasiswaId = mahasiswaId,
    namaTugas = namaTugas,
    detailTugas = detailTugas,
    deadline = deadline
)

fun Tugas.toUiStateTugas(isEntryValid: Boolean = false): UIStateTugas = UIStateTugas(
    detailTugas = this.toDetailTugas(),
    isEntryValid = isEntryValid
)

fun Tugas.toDetailTugas(): DetailTugas = DetailTugas(
    id = id,
    mahasiswaId = mahasiswaId,
    namaTugas = namaTugas,
    detailTugas = detailTugas,
    deadline = deadline
)