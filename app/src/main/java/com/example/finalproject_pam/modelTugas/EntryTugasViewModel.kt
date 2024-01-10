package com.example.finalproject_pam.modelTugas

import com.example.finalproject_pam.data.Tugas

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