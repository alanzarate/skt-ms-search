package com.ucb.bo.sktmssearch.bl

import com.ucb.bo.sktmssearch.dto.ResponseDto
import com.ucb.bo.sktmssearch.exception.ParameterException
import com.ucb.bo.sktmssearch.model.Cloth
import com.ucb.bo.sktmssearch.repository.ClothRepository
import com.ucb.bo.sktmssearch.repository.ImageRepository
import com.ucb.bo.sktmssearch.utils.PageableSort
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import kotlin.math.ceil

@Service
@AllArgsConstructor
@NoArgsConstructor
class SearchBl @Autowired constructor(
    private val clothRepository: ClothRepository,
    private val imageRepository: ImageRepository
){
    fun getSearched(limit: Int, currentPage: Int, sortBy: String?, order: String?): ResponseEntity<ResponseDto<Any>>{
        try{
            
            val length = clothRepository.count()
            val totalPages = ( ceil(length/limit.toDouble())).toInt()

            var selectedSort = Sort.Direction.ASC
            if(!order.isNullOrEmpty()) {
                if(order.lowercase()== "desc"){
                    selectedSort = Sort.Direction.DESC
                }
            }
            val page: MutableIterable<Cloth> =
                if (sortBy.isNullOrEmpty()){
                clothRepository.findAllByAvailable(
                    PageRequest.of(
                        (currentPage-1), limit
                    ) ,
                    true
                )
                }else{
                    clothRepository.findAllByAvailable(
                        PageRequest.of(
                            (currentPage-1), limit
                        ).withSort(
                            Sort.by(selectedSort)
                        ) ,
                        true
                    )
                }

            var clothesHashMap = ArrayList<HashMap<String, Any>>()
            for (cloth in page){
                val mapArq = HashMap<String, Any>()
                mapArq["id"] = cloth.clothId
                mapArq["name"] = cloth.name
                mapArq["user_id"] = cloth.userId
                mapArq["description"] = cloth.description
                mapArq["images"] = imageRepository.findByClothAndAvailable(cloth, true)
                clothesHashMap.add(mapArq)
            }
            var newMap = HashMap<String, Any>()
            newMap["clothes"] = clothesHashMap
            newMap["limit"] = limit
            newMap["current_page"] = currentPage
            newMap["total_pages"] = totalPages
            newMap["total_values"] = length
            return ResponseEntity(ResponseDto(newMap, null, true), HttpStatus.OK)
        }catch (ex: ParameterException){
            return ResponseEntity(ResponseDto(null, ex.message, false), HttpStatus.BAD_REQUEST)

        }catch (ex2: Exception){
            return ResponseEntity(ResponseDto(null, ex2.message, false), HttpStatus.NOT_ACCEPTABLE)
        }
    }
}