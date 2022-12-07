package com.vanaco.recruit.feature_candidate.domain.use_case

import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.repository.CandidateRepository
import com.vanaco.recruit.feature_candidate.domain.util.CandidateOrder
import com.vanaco.recruit.feature_candidate.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCandidatesUseCase(
    private val repository: CandidateRepository
) {

    operator fun invoke(
        candidateOrder: CandidateOrder = CandidateOrder.Date(OrderType.Descending)
    ): Flow<List<Candidate>> {
        return repository.getCandidates().map { candidates ->
            when (candidateOrder.orderType) {
                is OrderType.Ascending -> {
                    when (candidateOrder) {
                        is CandidateOrder.Name -> candidates.sortedBy { it.name.lowercase() }
                        is CandidateOrder.JobTitle -> candidates.sortedBy { it.jobTitle.lowercase() }
                        is CandidateOrder.Date -> candidates.sortedBy { it.timestamp }
                        is CandidateOrder.Color -> candidates.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (candidateOrder) {
                        is CandidateOrder.Name -> candidates.sortedByDescending { it.name.lowercase() }
                        is CandidateOrder.JobTitle -> candidates.sortedByDescending { it.jobTitle.lowercase() }
                        is CandidateOrder.Date -> candidates.sortedByDescending { it.timestamp }
                        is CandidateOrder.Color -> candidates.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}