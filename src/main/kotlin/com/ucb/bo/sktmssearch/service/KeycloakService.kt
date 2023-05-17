package com.ucb.bo.sktmssearch.service

import com.ucb.bo.sktmssearch.config.ConfigFeignClient
import com.ucb.bo.sktmssearch.dto.KeycloakTokenDto
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "keycloak",
    url="\${keycloak.auth-server-url}",
    configuration = [ConfigFeignClient::class])
interface KeycloakService {
    @PostMapping(value = ["/realms/\${keycloak.realm}/protocol/openid-connect/token"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getMsToken(@RequestBody body: Map<String, *>):KeycloakTokenDto

}