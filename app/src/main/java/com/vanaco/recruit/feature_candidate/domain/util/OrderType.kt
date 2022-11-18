package com.vanaco.recruit.feature_candidate.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}