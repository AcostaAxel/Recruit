package com.vanaco.recruit.feature_candidate.domain.util

sealed class CandidateOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : CandidateOrder(orderType)
    class Date(orderType: OrderType) : CandidateOrder(orderType)
    class Color(orderType: OrderType) : CandidateOrder(orderType)

    fun copy(orderType: OrderType): CandidateOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}