package com.vanaco.recruit.feature_candidate.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateDao {

    @Query("SELECT * FROM candidate")
    fun getCandidates(): Flow<List<Candidate>>

    @Query("SElECT * FROM candidate WHERE id = :id")
    suspend fun getCandidateById(id: Int): Candidate?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCandidate(candidate: Candidate)

    @Delete
    suspend fun deleteCandidate(candidate: Candidate)
}