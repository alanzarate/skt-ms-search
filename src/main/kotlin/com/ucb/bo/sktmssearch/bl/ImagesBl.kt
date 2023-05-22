package com.ucb.bo.sktmssearch.bl

import com.ucb.bo.sktmssearch.dto.ClothDto
import com.ucb.bo.sktmssearch.dto.FileDto
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
class ImagesBl @Autowired constructor(
    private val clothRepository: ClothRepository,
    private val imageService: ImageService,
    private val keycloakBl: KeycloakBl
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    fun getImagesAsFileDto(clothDto: ClothDto, token:String){
        val secondResult = clothRepository.getImagesInformation( clothDto.clothId.toString() )
        val arrayList: ArrayList<FileDto> = ArrayList()
        for (image in secondResult){
            logger.info("Ready to get image: $image")
            val response = imageService.getSignedImage("Bearer $token", image.uuidFile!!)

            val sign = response.body!!.data!!.url
            val fileDto: FileDto = image.adapterFileDto(sign)
            arrayList.add(fileDto)
        }
        clothDto.images = arrayList
        //listResponse.add(clothDto)
    }

    fun getImagesOfProductsAsFileDto(clothDto: ClothDto, token:String){
        val secondResult = clothRepository.getImagesInformationProducts( clothDto.clothId.toString() )
        val arrayList: ArrayList<FileDto> = ArrayList()
        for (image in secondResult){
            logger.info("Ready to get image: $image")
            val response = imageService.getSignedImage("Bearer $token", image.uuidFile!!)

            val sign = response.body!!.data!!.url
            val fileDto: FileDto = image.adapterFileDto(sign)
            arrayList.add(fileDto)
        }
        clothDto.images = arrayList
        //listResponse.add(clothDto)
    }
}