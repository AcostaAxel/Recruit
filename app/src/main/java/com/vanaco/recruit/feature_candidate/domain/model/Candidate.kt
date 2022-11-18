package com.vanaco.recruit.feature_candidate.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vanaco.recruit.ui.theme.*

@Entity
data class Candidate(
    val name: String,
    @ColumnInfo(name = "jobTitle", defaultValue = "0")
    val jobTitle: String,
    val contact: String,
    val languages: String,
    val salary: String,
    val comments: String,
    val offer: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val candidateColors = listOf(Red, Orange, Yellow, Green, Blue)
    }
}

class InvalidCandidateException(message: String) : Exception(message)