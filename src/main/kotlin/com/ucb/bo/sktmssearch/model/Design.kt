package com.ucb.bo.sktmssearch.model

import com.ucb.bo.sktmssearch.dto.ClothDto

data class Design (
    var clothId: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val userId: Int? = null,
    val available: String? = null,
){

    fun adapterClothDto():ClothDto{
        return ClothDto(
            clothId = clothId,
            description = description,
            name = name,
            userId = userId,

        )
    }

}