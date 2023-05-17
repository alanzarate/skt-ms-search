package com.ucb.bo.sktmssearch.bl

import com.ucb.bo.sktmssearch.dto.ClothDto
import com.ucb.bo.sktmssearch.dto.FileDto
import com.ucb.bo.sktmssearch.repository.ClothRepository
import com.ucb.bo.sktmssearch.service.ImageService
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
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
    fun getImagesAsFileDto(clothDto: ClothDto){
        val secondResult = clothRepository.getImagesInformation( clothDto.clothId.toString() )
        val arrayList: ArrayList<FileDto> = ArrayList()
        for (image in secondResult){
            val sign = imageService.getSignedImage(keycloakBl.getAuthorizationToken(), image.uuidFile!!)
            val fileDto: FileDto = image.adapterFileDto(sign.url)
            arrayList.add(fileDto)
        }
        clothDto.images = arrayList
        //listResponse.add(clothDto)
    }
}