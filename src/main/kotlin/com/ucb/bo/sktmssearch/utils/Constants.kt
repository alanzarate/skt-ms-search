package com.ucb.bo.sktmssearch.utils

import org.springframework.data.domain.Sort

enum class Constants {
    SILVER, GOLD, PLATINUM
}
enum class PageableSort(val sort: Sort.Direction){
    ASCENDANT(Sort.Direction.ASC),
    DESCENDANT(Sort.Direction.DESC)
}