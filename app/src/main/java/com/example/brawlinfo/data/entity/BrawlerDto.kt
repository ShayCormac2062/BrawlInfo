package com.example.brawlinfo.data.entity

import com.example.brawlinfo.domain.model.Brawler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrawlerDto(
    val avatarId: Int?,
    @SerialName("class")
    val category: ClassDto?,
    val description: String?,
    val gadgets: List<GadgetDto>?,
    val hash: String?,
    val id: Int?,
    val imageUrl: String?,
    val imageUrl2: String?,
    val imageUrl3: String?,
    val link: String?,
    val name: String?,
    val path: String?,
    val rarity: RarityDto?,
    val released: Boolean?,
    val starPowers: List<StarPowerDto>?,
    val unlock: Int?,
    val version: Int?,
    val videos: List<VideoDto>?
) {

    fun toBrawler() = Brawler(
        avatarId = avatarId,
        category = category?.toClass(),
        description = description,
        gadgets = gadgets?.map { it.toGadget() },
        id = id,
        imageUrl = imageUrl,
        imageUrl2 = imageUrl2,
        imageUrl3 = imageUrl3,
        name = name,
        rarity = rarity?.toRarity(),
        starPowers = starPowers?.map { it.toStarPower() },
        videos = videos?.map { it.toVideo() }
    )
}