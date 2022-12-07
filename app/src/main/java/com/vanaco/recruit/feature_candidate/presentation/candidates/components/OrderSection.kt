package com.vanaco.recruit.feature_candidate.presentation.candidates.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vanaco.recruit.feature_candidate.domain.util.CandidateOrder
import com.vanaco.recruit.feature_candidate.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    candidateOrder: CandidateOrder = CandidateOrder.Date(OrderType.Descending),
    onOrderChange: (CandidateOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Name",
                selected = candidateOrder is CandidateOrder.Name,
                onSelect = { onOrderChange(CandidateOrder.Name(candidateOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Job Title",
                selected = candidateOrder is CandidateOrder.JobTitle,
                onSelect = { onOrderChange(CandidateOrder.JobTitle(candidateOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Date",
                selected = candidateOrder is CandidateOrder.Date,
                onSelect = { onOrderChange(CandidateOrder.Date(candidateOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Color",
                selected = candidateOrder is CandidateOrder.Color,
                onSelect = { onOrderChange(CandidateOrder.Color(candidateOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = candidateOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(candidateOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Descending",
                selected = candidateOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(candidateOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}