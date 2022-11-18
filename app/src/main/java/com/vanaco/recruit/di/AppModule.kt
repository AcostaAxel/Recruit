package com.vanaco.recruit.di

import android.app.Application
import androidx.room.Room
import com.vanaco.recruit.feature_candidate.data.data_source.CandidateDatabase
import com.vanaco.recruit.feature_candidate.data.repository.CandidateRepositoryImpl
import com.vanaco.recruit.feature_candidate.domain.repository.CandidateRepository
import com.vanaco.recruit.feature_candidate.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCandidateDatabase(app: Application): CandidateDatabase {
        return Room.databaseBuilder(
            app,
            CandidateDatabase::class.java,
            CandidateDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCandidateRepository(db: CandidateDatabase): CandidateRepository {
        return CandidateRepositoryImpl(db.candidateDao)
    }

    @Provides
    @Singleton
    fun provideCandidateUseCases(repository: CandidateRepository): CandidateUseCase {
        return CandidateUseCase(
            getCandidatesUseCase = GetCandidatesUseCase(repository),
            deleteCandidateUseCase = DeleteCandidateUseCase(repository),
            addCandidateUseCase = AddCandidateUseCase(repository),
            getCandidateUseCase = GetCandidateUseCase(repository)
        )
    }
}