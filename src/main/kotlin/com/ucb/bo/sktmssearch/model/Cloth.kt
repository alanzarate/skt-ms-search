package com.ucb.bo.sktmssearch.model

import com.ucb.bo.sktmssearch.dto.ClothDto


data class Cloth(
    var productId: Int? = null ,
    var name: String? = null,
    var price: Double? = null,
    var description: String? = null,
    var stock: Int? = null,
    var available: Boolean? = null,
    var type: String? = null,
    var color: String? = null,
    var style: String? = null,
    var size: String? = null

){
    fun adapterClothDto(): ClothDto {
        return ClothDto(
            clothId = productId,
            description = description,
            name = name,
            )
    }

}