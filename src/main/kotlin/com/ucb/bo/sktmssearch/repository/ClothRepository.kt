package com.ucb.bo.sktmssearch.repository

import com.ucb.bo.sktmssearch.model.Cloth
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClothRepository: CrudRepository<Cloth, Long> {

    fun findAll(by: Sort): MutableIterable<Cloth>
//https://stackoverflow.com/questions/58509156/jpa-find-by-with-multi-conditions
    fun findAllByAvailable(page: Pageable, available: Boolean): MutableIterable<Cloth>
}