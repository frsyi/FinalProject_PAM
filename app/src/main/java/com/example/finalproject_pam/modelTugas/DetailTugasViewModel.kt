package com.example.finalproject_pam.modelTugas

data class ItemDetailTugasUiState(
    val outOfStock: Boolean = true,
    val detailTugas: DetailTugas = DetailTugas()
)