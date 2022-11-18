package com.vanaco.recruit.feature_candidate.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vanaco.recruit.feature_candidate.domain.model.Candidate

@Database(
    entities = [Candidate::class],
    version = 1
)

abstract class CandidateDatabase : RoomDatabase() {

    abstract val candidateDao: CandidateDao

    companion object {
        const val DATABASE_NAME = "candidates_db"
    }
}