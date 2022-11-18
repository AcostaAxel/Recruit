package com.vanaco.recruit.feature_candidate.presentation.add_edit_candidate

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.model.InvalidCandidateException
import com.vanaco.recruit.feature_candidate.domain.use_case.CandidateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCandidateViewModel @Inject constructor(
    private val candidateUseCases: CandidateUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _candidateName = mutableStateOf(
        CandidateTextFieldState(
            hint = "Name"
        )
    )
    val candidateName: State<CandidateTextFieldState> = _candidateName

    private val _candidateJobTitle = mutableStateOf(
        CandidateTextFieldState(
            hint = "Job Title"
        )
    )
    val candidateJobTitle: State<CandidateTextFieldState> = _candidateJobTitle

    private val _candidateContact = mutableStateOf(
        CandidateTextFieldState(
            hint = "Contact"
        )
    )
    val candidateContact: State<CandidateTextFieldState> = _candidateContact

    private val _candidateLanguages = mutableStateOf(
        CandidateTextFieldState(
            hint = "Languages"
        )
    )
    val candidateLanguages: State<CandidateTextFieldState> = _candidateLanguages

    private val _candidateSalary = mutableStateOf(
        CandidateTextFieldState(
            hint = "Salary"
        )
    )
    val candidateSalary: State<CandidateTextFieldState> = _candidateSalary

    private val _candidateComments = mutableStateOf(
        CandidateTextFieldState(
            hint = "Comments"
        )
    )
    val candidateComments: State<CandidateTextFieldState> = _candidateComments

    private val _candidateOffer = mutableStateOf(
        CandidateTextFieldState(
            hint = "Offer"
        )
    )
    val candidateOffer: State<CandidateTextFieldState> = _candidateOffer

    private val _candidateColor = mutableStateOf(Candidate.candidateColors.random().toArgb())
    val candidateColor: State<Int> = _candidateColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentCandidateId: Int? = null

    init {
        savedStateHandle.get<Int>("candidateId")?.let { candidateId ->
            if (candidateId != -1) {
                viewModelScope.launch {
                    candidateUseCases.getCandidateUseCase(candidateId)?.also { candidate ->
                        currentCandidateId = candidate.id
                        _candidateName.value = candidateName.value.copy(
                            text = candidate.name,
                            isHintVisible = false
                        )
                        _candidateJobTitle.value = _candidateJobTitle.value.copy(
                            text = candidate.jobTitle,
                            isHintVisible = false
                        )
                        _candidateContact.value = _candidateContact.value.copy(
                            text = candidate.contact,
                            isHintVisible = false
                        )
                        _candidateLanguages.value = _candidateLanguages.value.copy(
                            text = candidate.languages,
                            isHintVisible = false
                        )
                        _candidateSalary.value = _candidateSalary.value.copy(
                            text = candidate.salary,
                            isHintVisible = false
                        )
                        _candidateComments.value = _candidateComments.value.copy(
                            text = candidate.comments,
                            isHintVisible = false
                        )
                        _candidateOffer.value = _candidateOffer.value.copy(
                            text = candidate.offer,
                            isHintVisible = false
                        )
                        _candidateColor.value = candidate.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditCandidateEvent) {
        when (event) {
            is AddEditCandidateEvent.EnteredName -> {
                _candidateName.value = candidateName.value.copy(
                    text = event.value
                )
            }
            is AddEditCandidateEvent.ChangeNameFocus -> {
                _candidateName.value = candidateName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            candidateName.value.text.isBlank()
                )
            }
            is AddEditCandidateEvent.EnteredJobTitle -> {
                _candidateJobTitle.value = _candidateJobTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditCandidateEvent.ChangeJobTitleFocus -> {
                _candidateJobTitle.value = _candidateJobTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            candidateJobTitle.value.text.isBlank()
                )
            }
            is AddEditCandidateEvent.EnteredContact -> {
                _candidateContact.value = _candidateContact.value.copy(
                    text = event.value
                )
            }
            is AddEditCandidateEvent.ChangeContactFocus -> {
                _candidateContact.value = _candidateContact.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            candidateContact.value.text.isBlank()
                )
            }
            is AddEditCandidateEvent.EnteredLanguages -> {
                _candidateLanguages.value = _candidateLanguages.value.copy(
                    text = event.value
                )
            }
            is AddEditCandidateEvent.ChangeLanguagesFocus -> {
                _candidateLanguages.value = _candidateLanguages.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            candidateLanguages.value.text.isBlank()
                )
            }
            is AddEditCandidateEvent.EnteredSalary -> {
                _candidateSalary.value = _candidateSalary.value.copy(
                    text = event.value
                )
            }
            is AddEditCandidateEvent.ChangeSalaryFocus -> {
                _candidateSalary.value = _candidateSalary.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            candidateSalary.value.text.isBlank()
                )
            }
            is AddEditCandidateEvent.EnteredComments -> {
                _candidateComments.value = _candidateComments.value.copy(
                    text = event.value
                )
            }
            is AddEditCandidateEvent.ChangeCommentsFocus -> {
                _candidateComments.value = _candidateComments.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _candidateComments.value.text.isBlank()
                )
            }
            is AddEditCandidateEvent.EnteredOffer -> {
                _candidateOffer.value = _candidateOffer.value.copy(
                    text = event.value
                )
            }
            is AddEditCandidateEvent.ChangeOfferFocus -> {
                _candidateOffer.value = _candidateOffer.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            candidateOffer.value.text.isBlank()
                )
            }
            is AddEditCandidateEvent.ChangeColor -> {
                _candidateColor.value = event.color
            }
            is AddEditCandidateEvent.SaveCandidate -> {
                viewModelScope.launch {
                    try {
                        candidateUseCases.addCandidateUseCase(
                            Candidate(
                                name = candidateName.value.text,
                                jobTitle = candidateJobTitle.value.text,
                                contact = candidateContact.value.text,
                                languages = candidateLanguages.value.text,
                                salary = candidateSalary.value.text,
                                comments = candidateComments.value.text,
                                offer = candidateOffer.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = candidateColor.value,
                                id = currentCandidateId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveCandidate)
                    } catch (e: InvalidCandidateException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn´t save candidate"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveCandidate : UiEvent()
    }
}