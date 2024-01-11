package com.example.finalproject_pam.ui.halamanMahasiswa

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.modelMahasiswa.HomeMahasiswaViewModel
import com.example.finalproject_pam.modelMahasiswa.PenyediaViewModel
import com.example.finalproject_pam.navigasi.DestinasiNavigasi

object DestinasiHomeMahasiswa: DestinasiNavigasi {
    override val route = "homeMahasiswa"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMahasiswaScreen(
    navigateToItemEntry: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeMahasiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}