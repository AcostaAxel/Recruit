package com.vanaco.recruit.feature_candidate.domain.use_case

import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.model.InvalidCandidateException
import com.vanaco.recruit.feature_candidate.domain.repository.CandidateRepository

class AddCandidateUseCase(
    private val repository: CandidateRepository
) {

    @Throws(InvalidCandidateException::class)
    suspend operator fun invoke(candidate: Candidate) {
        if (candidate.name.isBlank()) {
            throw InvalidCandidateException("The name of the candidate can´t be empty")
        }
        if (candidate.jobTitle.isBlank()) {
            throw InvalidCandidateException("Job Title of the candidate can´t be empty")
        }
        if (candidate.contact.isBlank()) {
            throw InvalidCandidateException("Contact of the candidate can´t be empty")
        }
        repository.insertCandidate(candidate)
    }
}