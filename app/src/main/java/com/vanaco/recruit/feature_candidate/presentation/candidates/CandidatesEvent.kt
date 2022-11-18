package com.vanaco.recruit.feature_candidate.presentation.candidates

import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.util.CandidateOrder

sealed class CandidatesEvent {
    data class Order(val candidateOrder: CandidateOrder): CandidatesEvent()
    data class DeleteCandidate(val candidate: Candidate): CandidatesEvent()
    object RestoreCandidate: CandidatesEvent()
    object ToggleOrderSection: CandidatesEvent()
}