package com.ucb.bo.sktmssearch.controller

import com.ucb.bo.sktmssearch.bl.ClothBl
import com.ucb.bo.sktmssearch.dto.ResponseDto
import com.ucb.bo.sktmssearch.dto.SearchDto
import com.ucb.bo.sktmssearch.exception.ParameterException
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Slf4j
@RequestMapping("/api/v1/product")
class ProductController @Autowired constructor(
    private val clothBl: ClothBl
){
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    /*
    POST: /api/v1/product
    Body: (JSON)
    {
    page: (Int) (required)
        -> para paginacion , empieza desde 1
    limit: (Int) (required)
        -> para paginacion , valores > 0
    title: (String)
        -> busqueda por el nombre del producto o prenda
    color: (String)
        -> filtro por color (Ex, #ff5733, ), solo minusculas
    type: (String)
        -> filtro por tipo, el nombre del tipo debe ser exacto ( Ex. Polera, pantalon)
     size: (String)
        -> filtro por size (Ex. L, XL, ...)
     style: (String)
        -> filtro por estilo (Ex. formal, casual)
     priceLessThan:  (Double) **
     priceGreaterThan: (Double) **
        -> filtro por rango de precios,



    }
    Response: (JSON)
    {
        "data": Array?[
            {
                "cloth_id": Int,
                "name": String,
                "description": String,
                "user_id": Int,
                "images": Array?[
                    {
                        "title": String,
                        "description": String,
                        "url": String,
                        "filename": String,
                        "uuid_file": String,
                    }
                ]
            }
        ],
        "message": String? ,
        "success": Boolean
    }
     */
    @PostMapping()
    fun getDesignData(@RequestBody requestBody: SearchDto?): ResponseEntity<ResponseDto<Any>> {
        try{

            if (requestBody == null) throw ParameterException("Body faltante")
            logger.info("(POST) getDesignData: user wants data $requestBody ")
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