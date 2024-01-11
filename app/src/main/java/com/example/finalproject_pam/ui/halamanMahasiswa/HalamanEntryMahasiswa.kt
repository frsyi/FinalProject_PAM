package com.example.finalproject_pam.ui.halamanMahasiswa

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.modelMahasiswa.EntryMahasiswaViewModel
import com.example.finalproject_pam.modelMahasiswa.PenyediaViewModel
import com.example.finalproject_pam.navigasi.DestinasiNavigasi

object DestinasiEntryMahasiswa: DestinasiNavigasi {
    override val route = "item_entry_mahasiswa"
    override val titleRes = R.string.entry_mahasiswa
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryMahasiswaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryMahasiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}
