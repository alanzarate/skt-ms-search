package com.ucb.bo.sktmssearch.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ClothDto(
    val clothId: Int? = null,
    val color: String? = null,
    val type: String? = null,
    val style: String? = null,
    val size: String? = null,
    val price: Double? = null,
    val name: String? = null,
    val description: String? = null,
    val userId: Int? = null,
    var images: ArrayList<FileDto>? = null,

    ) {
}