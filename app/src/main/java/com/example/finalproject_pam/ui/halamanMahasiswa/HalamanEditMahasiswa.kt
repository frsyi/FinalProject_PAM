package com.example.finalproject_pam.ui.halamanMahasiswa

import com.example.finalproject_pam.R
import com.example.finalproject_pam.navigasi.DestinasiNavigasi

object ItemEditMahasiswaDestination : DestinasiNavigasi {
    override val route = "item_edit_mahasiswa"
    override val titleRes = R.string.edit_mahasiswa
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}