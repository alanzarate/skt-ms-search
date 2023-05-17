package com.ucb.bo.sktmssearch.controller

import com.ucb.bo.sktmssearch.bl.ClothBl
import com.ucb.bo.sktmssearch.dto.ResponseDto
import com.ucb.bo.sktmssearch.dto.SearchDto
import com.ucb.bo.sktmssearch.exception.ParameterException
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
@RequestMapping("/api/v1/product")
class ProductController @Autowired constructor(
    private val clothBl: ClothBl
){
    @PostMapping()
    fun getDesignData(@ModelAttribute requestBody: SearchDto?): ResponseEntity<ResponseDto<Any>> {
        try{
            if (requestBody == null) throw ParameterException("Body faltante")
            val result = clothBl.getSearchedByParams(requestBody)
            return ResponseEntity
                .ok()
                .body(
                    ResponseDto( result,null, true)
                )
        }catch (pe: ParameterException){
            return ResponseEntity
                .badRequest()
                .body(
                    ResponseDto(null, pe.message, false )
                )
        }catch (ex: Exception){
            return ResponseEntity
                .badRequest()
                .body(
                    ResponseDto(null, ex.message, false )
                )
        }
    }
}