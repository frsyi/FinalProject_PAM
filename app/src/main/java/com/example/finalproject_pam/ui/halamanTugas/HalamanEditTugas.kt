package com.example.finalproject_pam.ui.halamanTugas

import com.example.finalproject_pam.R
import com.example.finalproject_pam.navigasi.DestinasiNavigasi

object EditTugasDestination : DestinasiNavigasi {
    override val route = "item_edit_tugas"
    override val titleRes = R.string.edit_tugas
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}