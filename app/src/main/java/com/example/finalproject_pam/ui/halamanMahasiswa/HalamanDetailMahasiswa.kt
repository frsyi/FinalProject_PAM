package com.example.finalproject_pam.ui.halamanMahasiswa

import com.example.finalproject_pam.R
import com.example.finalproject_pam.navigasi.DestinasiNavigasi

object DetailMahasiswaDestination : DestinasiNavigasi {
    override val route = "item_detail_mahasiswa"
    override val titleRes = R.string.detail_mahasiswa
    const val mahasiswaIdArg = "itemId"
    val routeWithArgs = "$route/{$mahasiswaIdArg}"
}