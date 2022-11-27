package com.vanaco.recruit.feature_candidate.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.vanaco.recruit.feature_candidate.data.repository.FakeCandidateRepository
import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.util.CandidateOrder
import com.vanaco.recruit.feature_candidate.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCandidatesUseCaseTest {

    private lateinit var getCandidateUseCase: GetCandidatesUseCase
    private lateinit var fakeRepository: FakeCandidateRepository


    @Before
    fun setUp() {
        fakeRepository = FakeCandidateRepository()
        getCandidateUseCase = GetCandidatesUseCase(fakeRepository)

        val candidatesToInsert = mutableListOf<Candidate>()
        ('a'..'z').forEachIndexed { index, c ->
            candidatesToInsert.add(
                Candidate(
                    name = c.toString(),
                    jobTitle = c.toString(),
                    contact = c.toString(),
                    languages = c.toString(),
                    salary = c.toString(),
                    comments = c.toString(),
                    offer = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }
        candidatesToInsert.shuffle()
        runBlocking {
            candidatesToInsert.forEach { fakeRepository.insertCandidate(it) }
        }
    }

    @Test
    fun `Order candidates by name ascending, correct order`() = runBlocking {
        val candidates = getCandidateUseCase(CandidateOrder.Name(OrderType.Ascending)).first()

        for (i in 0..candidates.size - 2) {
            assertThat(candidates[i].name).isLessThan(candidates[i + 1].name)
        }
    }

    @Test
    fun `Order candidates by name descending, correct order`() = runBlocking {
        val candidates = getCandidateUseCase(CandidateOrder.Name(OrderType.Descending)).first()

        for (i in 0..candidates.size - 2) {
            assertThat(candidates[i].name).isGreaterThan(candidates[i + 1].name)
        }
    }

    @Test
    fun `Order candidates by date ascending, correct order`() = runBlocking {
        val candidates = getCandidateUseCase(CandidateOrder.Date(OrderType.Ascending)).first()

        for (i in 0..candidates.size - 2) {
            assertThat(candidates[i].timestamp).isLessThan(candidates[i + 1].timestamp)
        }
    }

    @Test
    fun `Order candidates by date descending, correct order`() = runBlocking {
        val candidates = getCandidateUseCase(CandidateOrder.Date(OrderType.Descending)).first()

        for (i in 0..candidates.size - 2) {
            assertThat(candidates[i].timestamp).isGreaterThan(candidates[i + 1].timestamp)
        }
    }

    @Test
    fun `Order candidates by color ascending, correct order`() = runBlocking {
        val candidates = getCandidateUseCase(CandidateOrder.Color(OrderType.Ascending)).first()

        for (i in 0..candidates.size - 2) {
            assertThat(candidates[i].color).isLessThan(candidates[i + 1].color)
        }
    }

    @Test
    fun `Order candidates by color descending, correct order`() = runBlocking {
        val candidates = getCandidateUseCase(CandidateOrder.Color(OrderType.Descending)).first()

        for (i in 0..candidates.size - 2) {
            assertThat(candidates[i].color).isGreaterThan(candidates[i + 1].color)
        }
    }
}

