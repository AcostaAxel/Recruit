package com.vanaco.recruit.feature_candidate.domain.use_case

data class CandidateUseCase(
    val getCandidatesUseCase: GetCandidatesUseCase,
    val deleteCandidateUseCase: DeleteCandidateUseCase,
    val addCandidateUseCase: AddCandidateUseCase,
    val getCandidateUseCase: GetCandidateUseCase
)