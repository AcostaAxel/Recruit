package com.vanaco.recruit.feature_candidate.presentation.candidates

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanaco.recruit.feature_candidate.domain.model.Candidate
import com.vanaco.recruit.feature_candidate.domain.use_case.CandidateUseCase
import com.vanaco.recruit.feature_candidate.domain.util.CandidateOrder
import com.vanaco.recruit.feature_candidate.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidatesViewModel @Inject constructor(
    private val candidateUseCases: CandidateUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CandidatesState())
    val state: State<CandidatesState> = _state

    private var recentlyDeletedCandidate: Candidate? = null

    private var getCandidatesJob: Job? = null

    init {
        getCandidates(CandidateOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: CandidatesEvent) {
        when (event) {
            is CandidatesEvent.Order -> {
                if (state.value.candidateOrder::class == event.candidateOrder::class &&
                    state.value.candidateOrder.orderType == event.candidateOrder.orderType
                ) {
                    return
                }
                getCandidates(event.candidateOrder)
            }
            is CandidatesEvent.DeleteCandidate -> {
                viewModelScope.launch {
                    candidateUseCases.deleteCandidateUseCase(event.candidate)
                    recentlyDeletedCandidate = event.candidate
                }
            }
            is CandidatesEvent.RestoreCandidate -> {
                viewModelScope.launch {
                    candidateUseCases.addCandidateUseCase(recentlyDeletedCandidate ?: return@launch)
                    recentlyDeletedCandidate = null
                }
            }
            is CandidatesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getCandidates(candidateOrder: CandidateOrder) {
        getCandidatesJob?.cancel()
        getCandidatesJob = candidateUseCases.getCandidatesUseCase(candidateOrder)
            .onEach { candidate ->
                _state.value = state.value.copy(
                    candidates = candidate,
                    candidateOrder = candidateOrder
                )
            }
            .launchIn(viewModelScope)
    }
}