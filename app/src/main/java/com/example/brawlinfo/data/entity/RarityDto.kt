package com.example.brawlinfo.data.entity

import com.example.brawlinfo.domain.model.Rarity
import kotlinx.serialization.Serializable

@Serializable
data class RarityDto(
    val color: String?,
    val id: Int?,
    val name: String?
) {

    fun toRarity() = Rarity(
        color = color,
        name = name
    )
}