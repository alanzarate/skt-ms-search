package com.ucb.bo.sktmssearch.bl

import com.ucb.bo.sktmssearch.dto.ClothDto
import com.ucb.bo.sktmssearch.dto.SearchDto
import com.ucb.bo.sktmssearch.repository.ClothRepository
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
@NoArgsConstructor
class ClothBl @Autowired constructor(
    private val clothRepository: ClothRepository,
    private val imagesBl: ImagesBl
){
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    fun getSearchedByParams(request: SearchDto): ArrayList<ClothDto> {
        println(request)
        request.validateData()
        logger.info("El usuario requiere la informacion de un Ropas con las caracteristicas ${request.title}")
        var strCommands = ""
        if (request.color != null)
            strCommands += " and cc.name = '${request.color}'"
        if (request.size != null)
            strCommands += " and sc.name = '${request.size}'"
        if (request.type != null)
            strCommands += " and tc.name = '${request.type}'"
        if (request.style != null)
            strCommands += " and fc.name = '${request.style}"
        if (request.priceLessThan != null)
            strCommands += " and pd.price < ${request.priceLessThan}"
        if (request.priceGreaterThan != null)
            strCommands += " and pd.price > ${request.priceGreaterThan}"
        if (request.title != null)
            strCommands += " and pd.name LIKE '${request.title}%' "


        val resultSearch = clothRepository.getProductsByCommands(strCommands, request.limit.toString(), request.getOffset().toString())
        val listResponse: ArrayList<ClothDto> = ArrayList()

        for (cloth in resultSearch){
            val clothDto = cloth.adapterClothDto()
            imagesBl.getImagesAsFileDto(clothDto)
            listResponse.add(clothDto)
        }

        return listResponse



    }
}