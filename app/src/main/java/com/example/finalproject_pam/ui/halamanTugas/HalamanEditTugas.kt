package com.example.finalproject_pam.ui.halamanTugas

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.modelMahasiswa.PenyediaViewModel
import com.example.finalproject_pam.modelTugas.EditTugasViewModel
import com.example.finalproject_pam.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

object EditTugasDestination : DestinasiNavigasi {
    override val route = "item_edit_tugas"
    override val titleRes = R.string.edit_tugas
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditTugasScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditTugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        EntryTugasBody(
            uiStateTugas = viewModel.tugasUiState,
            onTugasValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTugas()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}