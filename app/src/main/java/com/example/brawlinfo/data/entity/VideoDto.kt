package com.example.brawlinfo.data.entity

import com.example.brawlinfo.domain.model.Video
import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    val description: String?,
    val duration: String?,
    val name: String?,
    val previewUrl: String?,
    val type: Int?,
    val uploadDate: String?,
    val videoUrl: String?
) {

    fun toVideo() = Video(
        description = description,
        name = name,
        videoUrl = videoUrl
    )

}
