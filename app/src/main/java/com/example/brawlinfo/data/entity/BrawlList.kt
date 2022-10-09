package com.example.brawlinfo.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class BrawlList(
    val list: List<BrawlerDto>
)