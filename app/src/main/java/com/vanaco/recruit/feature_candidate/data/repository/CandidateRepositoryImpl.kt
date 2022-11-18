package com.vanaco.recruit.feature_candidate.data.repository

import com.vanaco.recruit.feature_candidate.data.data_source.CandidateDao
import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.repository.CandidateRepository
import kotlinx.coroutines.flow.Flow

class CandidateRepositoryImpl(
    private val dao: CandidateDao
) : CandidateRepository {
    override fun getCandidates(): Flow<List<Candidate>> {
        return dao.getCandidates()
    }

    override suspend fun getCandidateById(id: Int): Candidate? {
        return dao.getCandidateById(id)
    }

    override suspend fun insertCandidate(candidate: Candidate) {
        return dao.insertCandidate(candidate)
    }

    override suspend fun deleteCandidate(candidate: Candidate) {
        dao.deleteCandidate(candidate)
    }
}