package com.example.finalproject_pam.modelMahasiswa

import com.example.finalproject_pam.data.Mahasiswa

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

/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya */
fun DetailMahasiswa.toMahasiswa(): Mahasiswa = Mahasiswa(
    id = id,
    nama = nama,
    nim = nim,
    semester = semester
)

fun Mahasiswa.toUiStateMahasiswa(isEntryValid: Boolean = false): UIStateMahasiswa = UIStateMahasiswa(
    detailMahasiswa = this.toDetailMahasiswa(),
    isEntryValid = isEntryValid
)

fun Mahasiswa.toDetailMahasiswa(): DetailMahasiswa = DetailMahasiswa(
    id = id,
    nama = nama,
    nim = nim,
    semester = semester
)
