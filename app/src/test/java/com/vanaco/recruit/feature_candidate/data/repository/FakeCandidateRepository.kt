package com.vanaco.recruit.feature_candidate.data.repository

import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.repository.CandidateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCandidateRepository : CandidateRepository {

    private val candidates = mutableListOf<Candidate>()

    override fun getCandidates(): Flow<List<Candidate>> {
        return flow { emit(candidates) }
    }

    override suspend fun getCandidateById(id: Int): Candidate? {
        return candidates.find { it.id == id }
    }

    override suspend fun insertCandidate(candidate: Candidate) {
        candidates.add(candidate)
    }

    override suspend fun deleteCandidate(candidate: Candidate) {
        candidates.remove(candidate)
    }
}