package com.ucb.bo.sktmssearch.service

import com.ucb.bo.sktmssearch.config.ConfigFeignClient
import com.ucb.bo.sktmssearch.dto.FileDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "skt-ms-minio",
    url = "\${custom-config.minio-url}",
    configuration = [ConfigFeignClient::class])
interface ImageService {

    @GetMapping("/api/v1/files/{uuid}")
    fun getSignedImage(@RequestHeader("Authorization") token: String, @PathVariable(value = "uuid") uuid: String): FileDto
}