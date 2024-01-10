package com.example.finalproject_pam.modelMahasiswa

data class UIStateMahasiswa(
    val detailMahasiswa: DetailMahasiswa = DetailMahasiswa(),
    val isEntryValid: Boolean = false
)


data class DetailMahasiswa(
    val id: Int = 0,
    val nama: String = "",
    val nim: String = "",
    val semester: String = ""
)