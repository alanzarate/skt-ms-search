package com.ucb.bo.sktmssearch.bl

import com.ucb.bo.sktmssearch.service.KeycloakService
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
@NoArgsConstructor
class KeycloakBl @Autowired constructor(
    private val keycloakService: KeycloakService
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${keycloak.resource}")
    lateinit var clientId: String

    @Value("\${keycloak.credentials.secret}")
    lateinit var clientSecret: String


    fun getAuthorizationToken():String{
        val mapBody: Map<String,String> = mapOf(
            "client_id" to clientId,
            "client_secret" to clientSecret,
            "grant_type" to "client_credentials"
        )
        println(mapBody)
        val response = keycloakService.getMsToken(mapBody)
        println(response)
        if (response.access_token == null) throw Exception("Failded ocnecction token")
        return response.access_token!!
    }
}