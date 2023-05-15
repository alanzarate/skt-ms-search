package com.ucb.bo.sktmssearch.repository

import com.ucb.bo.sktmssearch.model.Cloth
import com.ucb.bo.sktmssearch.model.Image
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository: CrudRepository<Image, Long> {

    fun findByClothAndAvailable(cloth: Cloth, available: Boolean): List<Cloth>
}