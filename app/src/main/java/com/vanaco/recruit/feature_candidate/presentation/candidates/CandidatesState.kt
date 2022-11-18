package com.vanaco.recruit.feature_candidate.presentation.candidates

import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.util.CandidateOrder
import com.vanaco.recruit.feature_candidate.domain.util.OrderType

data class CandidatesState(
    val candidates: List<Candidate> = emptyList(),
    val candidateOrder: CandidateOrder = CandidateOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)