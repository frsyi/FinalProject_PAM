package com.example.finalproject_pam.ui.halamanMahasiswa

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.modelMahasiswa.EditMahasiswaViewModel
import com.example.finalproject_pam.modelMahasiswa.PenyediaViewModel
import com.example.finalproject_pam.navigasi.DestinasiNavigasi

object ItemEditMahasiswaDestination : DestinasiNavigasi {
    override val route = "item_edit_mahasiswa"
    override val titleRes = R.string.edit_mahasiswa
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditMahasiswaScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditMahasiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {

}