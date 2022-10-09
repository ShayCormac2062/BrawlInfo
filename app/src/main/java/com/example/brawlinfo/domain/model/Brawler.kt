package com.example.brawlinfo.domain.model

data class Brawler(
    val avatarId: Int? = null,
    val category: Class? = null,
    val description: String? = null,
    val gadgets: List<Gadget>? = null,
    val id: Int? = null,
    val imageUrl: String? = null,
    val imageUrl2: String? = null,
    val imageUrl3: String? = null,
    val name: String? = null,
    val rarity: Rarity? = null,
    val starPowers: List<StarPower>? = null,
    val videos: List<Video>? = null,
)
