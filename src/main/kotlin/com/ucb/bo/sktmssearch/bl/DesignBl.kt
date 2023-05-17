package com.ucb.bo.sktmssearch.bl


import com.ucb.bo.sktmssearch.dto.ClothDto
import com.ucb.bo.sktmssearch.dto.FileDto
import com.ucb.bo.sktmssearch.dto.SearchDto
import com.ucb.bo.sktmssearch.repository.ClothRepository
import com.ucb.bo.sktmssearch.service.ImageService
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
@NoArgsConstructor
class DesignBl @Autowired constructor(
    private val clothRepository: ClothRepository,
    private val imagesBl: ImagesBl

){
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    fun getInformationOfDesign(request: SearchDto): ArrayList<ClothDto> {
        println(request)
        request.validateData()
        logger.info("El usuario requiere la informacion de un Disenio con las caracteristicas ${request.title}")
        var strCommands = ""
        if (request.category != null)
            strCommands += " and ct.name = '${request.category}' "
        if (request.type != null)
            strCommands += " and tp.name = '${request.type}' "
        if (request.title != null)
            strCommands += " and cl.name LIKE '${request.title}%' "

        val resultSearch  = clothRepository.getDesignsByCommands(
            strCommands, request.limit.toString() , request.getOffset().toString() )
        val listResponse: ArrayList<ClothDto> =  ArrayList()
        for(value in resultSearch){
            val clothDto = value.adapterClothDto()
            imagesBl.getImagesAsFileDto(clothDto)
            listResponse.add(clothDto)


            /*
            val clothDto:ClothDto = value.adapterClothDto()
            val secondResult = clothRepository.getImagesInformation( value.clothId.toString() )
            val arrayList: ArrayList<FileDto> = ArrayList()
            for (image in secondResult){
                val sign = imageService.getSignedImage("", image.uuidFile!!)
                val fileDto: FileDto = image.adapterFileDto(sign.url)
                arrayList.add(fileDto)
            }
            clothDto.images = arrayList
            listResponse.add(clothDto)

             */
        }
        return listResponse
    }

}