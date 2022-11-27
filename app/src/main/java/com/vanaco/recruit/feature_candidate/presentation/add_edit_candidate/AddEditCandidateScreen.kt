package com.vanaco.recruit.feature_candidate.presentation.add_edit_candidate

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vanaco.recruit.core.util.TestTags
import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.presentation.add_edit_candidate.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditCandidateScreen(
    navController: NavController,
    candidateColor: Int,
    viewModel: AddEditCandidateViewModel = hiltViewModel()
) {
    val nameState = viewModel.candidateName.value
    val jobTitleState = viewModel.candidateJobTitle.value
    val contactState = viewModel.candidateContact.value
    val languagesState = viewModel.candidateLanguages.value
    val salaryState = viewModel.candidateSalary.value
    val commentsState = viewModel.candidateComments.value
    val offerState = viewModel.candidateOffer.value

    val scaffoldState = rememberScaffoldState()

    val candidateBackgroundAnimatable = remember {
        Animatable(
            Color(if (candidateColor != -1) candidateColor else viewModel.candidateColor.value)
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditCandidateViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditCandidateViewModel.UiEvent.SaveCandidate -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditCandidateEvent.SaveCandidate)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save candidate")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(candidateBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Candidate.candidateColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.candidateColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    candidateBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditCandidateEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text(text = "Name:", color = Color.DarkGray)
            TransparentHintTextField(
                text = nameState.text,
                hint = nameState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCandidateEvent.EnteredName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCandidateEvent.ChangeNameFocus(it))
                },
                isHintVisible = nameState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h4,
                testTag = TestTags.NAME_TEXT_FIELD
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Job Title:", color = Color.DarkGray)
            TransparentHintTextField(
                text = jobTitleState.text,
                hint = jobTitleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCandidateEvent.EnteredJobTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCandidateEvent.ChangeJobTitleFocus(it))
                },
                isHintVisible = jobTitleState.isHintVisible,
                textStyle = MaterialTheme.typography.h5,
                testTag = TestTags.JOBTITLE_TEXT_FIELD
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Contact:", color = Color.DarkGray)
            TransparentHintTextField(
                text = contactState.text,
                hint = contactState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCandidateEvent.EnteredContact(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCandidateEvent.ChangeContactFocus(it))
                },
                isHintVisible = contactState.isHintVisible,
                textStyle = MaterialTheme.typography.h5,
                testTag = TestTags.CONTACT_TEXT_FIELD
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Languages:", color = Color.DarkGray)
            TransparentHintTextField(
                text = languagesState.text,
                hint = languagesState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCandidateEvent.EnteredLanguages(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCandidateEvent.ChangeLanguagesFocus(it))
                },
                isHintVisible = languagesState.isHintVisible,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Salary:", color = Color.DarkGray)
            TransparentHintTextField(
                text = salaryState.text,
                hint = salaryState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCandidateEvent.EnteredSalary(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCandidateEvent.ChangeSalaryFocus(it))
                },
                isHintVisible = salaryState.isHintVisible,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Comments:", color = Color.DarkGray)
            TransparentHintTextField(
                text = commentsState.text,
                hint = commentsState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCandidateEvent.EnteredComments(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCandidateEvent.ChangeCommentsFocus(it))
                },
                isHintVisible = commentsState.isHintVisible,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Offer:", color = Color.DarkGray)
            TransparentHintTextField(
                text = offerState.text,
                hint = offerState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCandidateEvent.EnteredOffer(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCandidateEvent.ChangeOfferFocus(it))
                },
                isHintVisible = offerState.isHintVisible,
                textStyle = MaterialTheme.typography.h5
            )
        }
    }
}