package com.vanaco.recruit.feature_candidate.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vanaco.recruit.core.util.TestTags
import com.vanaco.recruit.di.AppModule
import com.vanaco.recruit.feature_candidate.presentation.add_edit_candidate.AddEditCandidateScreen
import com.vanaco.recruit.feature_candidate.presentation.candidates.CandidatesScreen
import com.vanaco.recruit.feature_candidate.presentation.util.Screen
import com.vanaco.recruit.ui.theme.RecruitAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class CandidatesEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            RecruitAppTheme {
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

    @Test
    fun saveNewCandidate_editAfterwards() {
        // Click on FAB to get to add candidate screen
        composeRule.onNodeWithContentDescription("Add candidate").performClick()

        // Enter texts in name, job title and contact text fields
        composeRule
            .onNodeWithTag(TestTags.NAME_TEXT_FIELD)
            .performTextInput("test-name")
        composeRule
            .onNodeWithTag(TestTags.JOBTITLE_TEXT_FIELD)
            .performTextInput("test-jobTitle")
        composeRule
            .onNodeWithTag(TestTags.CONTACT_TEXT_FIELD)
            .performTextInput("test-contact")
        // Save candidate
        composeRule.onNodeWithContentDescription("Save candidate").performClick()

        // Make sure there is a candidate in he list with our name, job title and contact
        composeRule.onNodeWithText("test-name").assertIsDisplayed()
        // Click on candidate to edit it
        composeRule.onNodeWithText("test-name").performClick()

        // Make sure the text fields are with the same content
        composeRule
            .onNodeWithTag(TestTags.NAME_TEXT_FIELD)
            .assertTextEquals("test-name")
        composeRule
            .onNodeWithTag(TestTags.JOBTITLE_TEXT_FIELD)
            .assertTextEquals("test-jobTitle")
        composeRule
            .onNodeWithTag(TestTags.CONTACT_TEXT_FIELD)
            .assertTextEquals("test-contact")
        // Add the text "2" to the name text field
        composeRule
            .onNodeWithTag(TestTags.NAME_TEXT_FIELD)
            .performTextInput("2")
        // Update the candidate
        composeRule.onNodeWithContentDescription("Save candidate").performClick()

        // Make sure the update was applied to the list
        composeRule.onNodeWithText("test-name2").assertIsDisplayed()
    }

    @Test
    fun saveNewCandidates_orderByNameDescending() {
        for (i in 1..3) {
            // Click on FAB to get to add candidate screen
            composeRule.onNodeWithContentDescription("Add candidate").performClick()

            // Enter texts in name, job title and contact text fields
            composeRule
                .onNodeWithTag(TestTags.NAME_TEXT_FIELD)
                .performTextInput(i.toString())
            composeRule
                .onNodeWithTag(TestTags.JOBTITLE_TEXT_FIELD)
                .performTextInput(i.toString())
            composeRule
                .onNodeWithTag(TestTags.CONTACT_TEXT_FIELD)
                .performTextInput(i.toString())
            // Save candidate
            composeRule.onNodeWithContentDescription("Save candidate").performClick()
        }

        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("Sort")
            .performClick()
        composeRule.onNodeWithContentDescription("Name")
            .performClick()
        composeRule.onNodeWithContentDescription("Descending")
            .performClick()

        composeRule.onAllNodesWithTag(TestTags.CANDIDATE_ITEM)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.CANDIDATE_ITEM)[1]
            .assertTextContains("2")
        composeRule.onAllNodesWithTag(TestTags.CANDIDATE_ITEM)[2]
            .assertTextContains("1")
    }
}