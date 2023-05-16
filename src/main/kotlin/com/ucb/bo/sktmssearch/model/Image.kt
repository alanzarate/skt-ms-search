package com.ucb.bo.sktmssearch.model

import com.ucb.bo.sktmssearch.dto.FileDto


data class Image(
    var imageId: Long? = null,
    var available: Boolean? = null,
    val uuidFile: String? = null,
    val filename: String? = null,
    val title: String? = null,
    val description: String? = null,
){
    fun adapterFileDto(url: String?):FileDto{
        return  FileDto(
            title = title,
            description = description,
            url = url,
            uuidFile = uuidFile,
            filename = filename,

        )
    }

}