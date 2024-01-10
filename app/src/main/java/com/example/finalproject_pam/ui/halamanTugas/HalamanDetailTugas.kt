package com.example.finalproject_pam.ui.halamanTugas

import com.example.finalproject_pam.R
import com.example.finalproject_pam.navigasi.DestinasiNavigasi

object DetailTugasDestination : DestinasiNavigasi {
    override val route = "item_detail_tugas"
    override val titleRes = R.string.detail_tugas
    const val tugasIdArg = "itemId"
    val routeWithArgs = "$route/{$tugasIdArg}"
}