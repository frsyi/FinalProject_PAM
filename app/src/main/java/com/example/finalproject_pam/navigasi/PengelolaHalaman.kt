package com.example.finalproject_pam.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalproject_pam.R
import com.example.finalproject_pam.ui.halamanMahasiswa.DestinasiEntryMahasiswa
import com.example.finalproject_pam.ui.halamanMahasiswa.DestinasiHalamanHome
import com.example.finalproject_pam.ui.halamanMahasiswa.DestinasiHomeMahasiswa
import com.example.finalproject_pam.ui.halamanMahasiswa.DetailMahasiswaDestination
import com.example.finalproject_pam.ui.halamanMahasiswa.DetailMahasiswaScreen
import com.example.finalproject_pam.ui.halamanMahasiswa.EntryMahasiswaScreen
import com.example.finalproject_pam.ui.halamanMahasiswa.HalamanHome
import com.example.finalproject_pam.ui.halamanMahasiswa.HomeMahasiswaScreen
import com.example.finalproject_pam.ui.halamanMahasiswa.ItemEditMahasiswaDestination
import com.example.finalproject_pam.ui.halamanMahasiswa.ItemEditMahasiswaScreen
import com.example.finalproject_pam.ui.halamanTugas.DestinasiEntryTugas
import com.example.finalproject_pam.ui.halamanTugas.DetailTugasDestination
import com.example.finalproject_pam.ui.halamanTugas.DetailTugasScreen
import com.example.finalproject_pam.ui.halamanTugas.EditTugasDestination
import com.example.finalproject_pam.ui.halamanTugas.EntryTugasScreen
import com.example.finalproject_pam.ui.halamanTugas.ItemEditTugasScreen

@Composable
fun PengelolaTugasMahasiswaApp(navController: NavHostController = rememberNavController()){
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MahasiswaToAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TugasToAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHalamanHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHalamanHome.route){
            HalamanHome(
                onNextButtonClicked = { navController.navigate(DestinasiHomeMahasiswa.route) }
            )
        }
        composable(DestinasiHomeMahasiswa.route){
            HomeMahasiswaScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryMahasiswa.route) },
                navigateBack = { navController.popBackStack() },
                onDetailClick = {
                    navController.navigate("${DetailMahasiswaDestination.route}/$it")
                }
            )
        }
        composable(DestinasiEntryMahasiswa.route){
            EntryMahasiswaScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            DetailMahasiswaDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailMahasiswaDestination.mahasiswaIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailMahasiswaScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = {
                    navController.navigate("${ItemEditMahasiswaDestination.route}/$it")
                },
                navigateToEntryTugas = { navController.navigate(DestinasiEntryTugas.route) },
                onDetailTugasClick = {
                    navController.navigate("${DetailTugasDestination.route}/$it")
                }
            )
        }
        composable(
            ItemEditMahasiswaDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditMahasiswaDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditMahasiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(DestinasiEntryTugas.route) {
            EntryTugasScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            DetailTugasDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailTugasDestination.tugasIdArg) {
                type = NavType.IntType
            })
        ){
            DetailTugasScreen(
                navigateToEditItem = {
                    navController.navigate("${EditTugasDestination.route}/$it")
                },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            EditTugasDestination.routeWithArgs,
            arguments = listOf(navArgument(EditTugasDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditTugasScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}