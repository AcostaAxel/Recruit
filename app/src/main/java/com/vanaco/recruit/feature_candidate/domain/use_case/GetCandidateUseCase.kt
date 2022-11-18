package com.vanaco.recruit.feature_candidate.domain.use_case

import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.repository.CandidateRepository

class GetCandidateUseCase(
    private val repository: CandidateRepository
) {
    suspend operator fun invoke(id: Int): Candidate? {
        return repository.getCandidateById(id)
    }
}