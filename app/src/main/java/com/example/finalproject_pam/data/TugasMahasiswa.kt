package com.example.finalproject_pam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblMahasiswa")
data class Mahasiswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val nim: String,
    val semester: String
)

@Entity(tableName = "tblTugas")
data class Tugas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mahasiswaId: Int,
    val namaTugas: String,
    val detailTugas: String,
    val deadline: String
)