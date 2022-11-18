package com.vanaco.recruit.feature_candidate.domain.repository

import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import kotlinx.coroutines.flow.Flow

interface CandidateRepository {

    fun getCandidates(): Flow<List<Candidate>>

    suspend fun getCandidateById(id: Int): Candidate?

    suspend fun insertCandidate(candidate: Candidate)

    suspend fun deleteCandidate(candidate: Candidate)
}