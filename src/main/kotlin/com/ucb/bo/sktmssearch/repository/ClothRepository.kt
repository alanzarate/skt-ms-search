package com.ucb.bo.sktmssearch.repository

import com.ucb.bo.sktmssearch.model.Cloth
import com.ucb.bo.sktmssearch.model.Design
import com.ucb.bo.sktmssearch.model.Image
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Component



@Component
@AllArgsConstructor
@NoArgsConstructor
interface ClothRepository{



    @Select( value = [
        "select cl.cloth_id, cl.name, cl.description, cl.user_id , cl.available " +
                "FROM  type_cloth tc, " +
                "type_prod tp, " +
                "cloth cl, " +
                "cloth_category clct, " +
                "category ct " +
                "WHERE cl.cloth_id = clct.cloth_id " +
                "and ct.category_id = clct.category_id " +
                "and tp.type_id  = tc.type_id " +
                "and cl.cloth_id = tc.cloth_id " +
                " \${command} " +
                "group by cl.cloth_id " +
                "order by cl.cloth_id DESC "+
                " LIMIT \${limit} " +
                " OFFSET \${offset};"
    ])
    fun getDesignsByCommands(command: String, limit: String, offset: String): List<Design>

    @Select( value = [
        " select " +
                "im.image_id , im.available, im.uuidFile, im.filename, im.title,\n" +
                "im.descriptio " +
                "from image im " +
                "where im.cloth_id = \${clothId}"
    ])
    fun getImagesInformation(clothId: String):List<Image>

    @Select( value=["SELECT pd.product_id , pd.name, pd.price, pd.description, pd.stock,pd.available, " +
            "tc.name as type, cc.name as color, fc.name as style, sc.name as size " +
            "from  product pd, " +
            "color_cloth cc, " +
            "formality_cloth fc,   " +
            "size_cloth sc, " +
            "type_prod tc " +
            "where pd.type_id = tc.type_id " +
            "and pd.color_cloth_id = cc.color_id " +
            "and pd.size_cloth_id = sc.size_id " +
            "and pd.formality_cloth_id = fc.formality_id   " +
            "\${command} " +
            "GROUP BY cl.cloth_id " +
            "ORDER BY pd.product_id DESC " +
            "LIMIT \${limit} " +
           "OFFSET \${offset}; "])
    fun getProductsByCommands(command: String,limit: String, offset: String): List<Cloth>

}