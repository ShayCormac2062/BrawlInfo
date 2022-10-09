package com.example.brawlinfo.data.entity

import com.example.brawlinfo.domain.model.StarPower
import kotlinx.serialization.Serializable

@Serializable
data class StarPowerDto(
    val description: String?,
    val id: Int?,
    val imageUrl: String?,
    val name: String?,
    val path: String?,
    val released: Boolean?,
    val version: Int?
) {

    fun toStarPower() = StarPower(
        description = description,
        imageUrl = imageUrl,
        name = name,
    )
}