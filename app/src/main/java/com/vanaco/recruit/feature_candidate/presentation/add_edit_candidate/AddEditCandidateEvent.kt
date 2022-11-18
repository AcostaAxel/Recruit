package com.vanaco.recruit.feature_candidate.presentation.add_edit_candidate

import androidx.compose.ui.focus.FocusState

sealed class AddEditCandidateEvent {
    data class EnteredName(val value: String): AddEditCandidateEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditCandidateEvent()

    data class EnteredContact(val value: String): AddEditCandidateEvent()
    data class ChangeContactFocus(val focusState: FocusState): AddEditCandidateEvent()

    data class EnteredLanguages(val value: String): AddEditCandidateEvent()
    data class ChangeLanguagesFocus(val focusState: FocusState): AddEditCandidateEvent()

    data class EnteredSalary(val value: String): AddEditCandidateEvent()
    data class ChangeSalaryFocus(val focusState: FocusState): AddEditCandidateEvent()

    data class EnteredComments(val value: String): AddEditCandidateEvent()
    data class ChangeCommentsFocus(val focusState: FocusState): AddEditCandidateEvent()

    data class EnteredOffer(val value: String): AddEditCandidateEvent()
    data class ChangeOfferFocus(val focusState: FocusState): AddEditCandidateEvent()

    data class ChangeColor(val color: Int): AddEditCandidateEvent()
    object SaveCandidate: AddEditCandidateEvent()
}