package com.example.brawlinfo.data.entity

import com.example.brawlinfo.domain.model.Class
import kotlinx.serialization.Serializable

@Serializable
data class ClassDto(
    val id: Int?,
    val name: String?
) {
    fun toClass() = Class(
        name = name
    )
}