package com.example.finalproject_pam.modelTugas

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