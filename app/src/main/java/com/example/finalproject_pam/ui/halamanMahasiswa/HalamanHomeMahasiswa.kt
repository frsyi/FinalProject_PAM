package com.example.finalproject_pam.ui.halamanMahasiswa

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.data.Mahasiswa
import com.example.finalproject_pam.modelMahasiswa.HomeMahasiswaViewModel
import com.example.finalproject_pam.modelMahasiswa.PenyediaViewModel
import com.example.finalproject_pam.navigasi.DestinasiNavigasi
import com.example.finalproject_pam.navigasi.MahasiswaToAppBar

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
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MahasiswaToAppBar(
                title = stringResource(DestinasiHomeMahasiswa.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_Large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_mahasiswa)
                )
            }
        }
    ){innerPadding ->
        val uiStateMahasiswa by viewModel.homeUiState.collectAsState()
        BodyHomeMahasiswa(
            itemMahasiswa = uiStateMahasiswa.listMahasiswa,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onMahasiswaClick = onDetailClick
        )
    }
}

@Composable
fun BodyHomeMahasiswa(
    itemMahasiswa: List<Mahasiswa>,
    modifier: Modifier = Modifier,
    onMahasiswaClick: (Int) -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemMahasiswa.isEmpty()){
            Text(
                text = stringResource(R.string.deskripsi_no_mahasiswa),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListMahasiswa(
                itemMahasiswa = itemMahasiswa,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onMahasiswaClick(it.id) }
            )
        }
    }
}

@Composable
fun ListMahasiswa(
    itemMahasiswa : List<Mahasiswa>,
    modifier: Modifier = Modifier,
    onItemClick: (Mahasiswa) -> Unit
){
    LazyColumn(modifier = Modifier){
        items(items = itemMahasiswa, key = {it.id}){person ->
            DataMahasiswa(
                mahasiswa = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataMahasiswa(
    mahasiswa: Mahasiswa,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_Large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = mahasiswa.nama,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.sem),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = mahasiswa.semester,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.card),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = mahasiswa.nim,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


@Composable
@Preview
fun DataMahasiswaPreview() {
    val sampleMahasiswa = Mahasiswa(id = 1, nama = "Fahma Rosyidah", nim = "20210140030", semester = "5")
    DataMahasiswa(mahasiswa = sampleMahasiswa)
}