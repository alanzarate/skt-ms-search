package com.ucb.bo.sktmssearch.dto

class ResponseDto<T>(
    val data: T?,
    val message: String?,
    val success: Boolean
) {
}