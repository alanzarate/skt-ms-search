package com.ucb.bo.sktmssearch.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.ucb.bo.sktmssearch.exception.ParameterException
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchDto (
    val page: Int? = null,
    val limit: Int? = null,
    val title: String? = null,
    val color: String? = null,
    val type: String? = null,
    val style: String? = null,
    val size: String? = null,
    val priceGreaterThan: Double? = null,
    val priceLessThan: Double? = null,
    val category: String? = null,


){
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun toString(): String {
        return "SearchDto(page=$page, limit=$limit, color=$color, type=$type, style=$style, size=$size, priceGreaterThan=$priceGreaterThan, priceLessThan=$priceLessThan, category=$category)"
    }

    fun validateData(){
        if ( limit == null)throw ParameterException("LIMIT invalid")
        if ( page == null)throw ParameterException("Page invalid")
        if ( priceGreaterThan != null){
            if( priceGreaterThan < 0) throw ParameterException("priceGreaterThan invalid")
        }
        if ( priceLessThan != null){
            if( priceLessThan < 0) throw ParameterException("priceLessThan invalid")
        }
        if (priceGreaterThan != null && priceLessThan != null ){
            if(priceLessThan >= priceGreaterThan) throw ParameterException("CHECK range")
        }
        if (limit <= 0) throw ParameterException("LIMIT invalid")
        if (page <= 0) throw ParameterException("Page invalid")

        checkLetter(color, ::color.name)
        checkLetter(type, ::type.name)
        checkLetter(style, ::style.name)
        checkLetter(size, ::size.name)
        checkLetter(category, ::category.name)
        checkLetter(color, ::color.name)
        checkLetter(title, ::title.name)
        logger.info("Todos los datos son correctos")

    }

    private fun checkLetter(variable: String?, variableInClass:String) {
        if (variable !=null ){
            if (variable.isEmpty() || variable.isBlank())
                throw ParameterException("$variableInClass invalid")
            if(!variable.onlyLetters())
                throw ParameterException("$variableInClass not only letters")
        }




    }

    private fun String.onlyLetters() = all { it.isLetter() }

    fun getOffset(): Int{
        return (page!!-1) * limit!!
    }

}