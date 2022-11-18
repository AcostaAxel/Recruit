package com.vanaco.recruit.feature_candidate.presentation.util

sealed class Screen(val route: String) {
    object CandidatesScreen : Screen("candidates_screen")
    object AddEditCandidateScreen : Screen("add_edit_candidates_screen")
}