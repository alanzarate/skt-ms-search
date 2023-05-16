package com.ucb.bo.sktmssearch.model




data class Cloth(
    var clothId: Long ,
    var name: String,
    var description: String,
    var available: Boolean,
    var price: Double,
    var color: String,
    var formality: String,
    var size: String,
    var type: String

){

}