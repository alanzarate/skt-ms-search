package com.ucb.bo.sktmssearch.bl

import com.ucb.bo.sktmssearch.dto.ClothDto
import com.ucb.bo.sktmssearch.dto.SearchDto
import com.ucb.bo.sktmssearch.repository.ClothRepository
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
@NoArgsConstructor
class ClothBl @Autowired constructor(
    private val clothRepository: ClothRepository
){
    fun getSearchedByParams(request: SearchDto): Any {
        println(request)
        request.validateData()
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
            strCommands += " and po.price < ${request.priceLessThan}"
        if (request.priceGreaterThan != null)
            strCommands += " and po.price > ${request.priceGreaterThan}"


        val resultSearch = clothRepository.getAbsoluteData(strCommands, request.limit.toString(), request.getOffset().toString())
        val listResponse: ArrayList<ClothDto> = ArrayList()

        for (cloth in resultSearch){
            val clothDto = cloth.adapterClothDto()
            imagesBl.getImagesAsFileDto(clothDto)
            listResponse.add(clothDto)
        }

        val res: ArrayList<ClothDto> = ArrayList()
        for(value in result){
            val result = clothRepository.getClothImages(value)
        }




    }
}