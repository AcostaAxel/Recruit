package com.vanaco.recruit.feature_candidate.domain.use_case

import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.repository.CandidateRepository

class DeleteCandidateUseCase(
    private val repository: CandidateRepository
) {
    suspend operator fun invoke(candidate: Candidate) {
        repository.deleteCandidate(candidate)
    }
}