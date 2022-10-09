package com.example.brawlinfo.data.entity

import com.example.brawlinfo.domain.model.Gadget
import kotlinx.serialization.Serializable

@Serializable
data class GadgetDto(
    val description: String?,
    val id: Int?,
    val imageUrl: String?,
    val name: String?,
    val path: String?,
    val released: Boolean?,
    val version: Int?
) {

    fun toGadget() = Gadget(
        description = description,
        id = id,
        imageUrl = imageUrl,
        name = name,
        path = path
    )

}