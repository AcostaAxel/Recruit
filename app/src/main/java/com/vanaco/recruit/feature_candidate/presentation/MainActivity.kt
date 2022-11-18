package com.vanaco.recruit.feature_candidate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vanaco.recruit.feature_candidate.presentation.add_edit_candidate.AddEditCandidateScreen
import com.vanaco.recruit.feature_candidate.presentation.candidates.CandidatesScreen
import com.vanaco.recruit.feature_candidate.presentation.util.Screen
import com.vanaco.recruit.ui.theme.RecruitAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            installSplashScreen()
            RecruitAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CandidatesScreen.route
                    ) {
                        composable(
                            route = Screen.CandidatesScreen.route
                        ) {
                            CandidatesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditCandidateScreen.route +
                                    "?candidateId={candidateId}&candidateColor={candidateColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "candidateId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "candidateColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("candidateColor") ?: -1
                            AddEditCandidateScreen(
                                navController = navController,
                                candidateColor = color
                            )
                        }
                    }
                }
            }
        }
    }
}