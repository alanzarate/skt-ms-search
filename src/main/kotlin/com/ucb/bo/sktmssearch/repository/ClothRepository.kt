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

    @Select(value = ["SELECT cl.cloth_id , cl.name, cl.description, cl.available, po.price," +
        "cc.name as color, fc.name as formality, sc.name as size, tc.name as type  from "+
        " cloth cl,"+
        " product_order po,"+
        " product p,"+
        " color_cloth cc,"+
        " formality_cloth fc,  "+
        " size_cloth sc,"+
        " type_cloth tc"+
        " where cl.cloth_id = po.cloth_id"+
        " and p.product_id = po.product_id"+
        " and cc.color_id = p.color_cloth_id"+
        " and fc.formality_id = p.formality_cloth_id"+
        " and sc.size_id = p.size_cloth_id"+
        " and tc.type_id = p.type_id"+
        " and cl.name LIKE '\${name}%'"+
        " \${commands}"+
        " ORDER BY cl.cloth_id DESC"+
        " LIMIT \${limit}"+
        " OFFSET \${offset};"])
    fun getAbsoluteData(commands:String,limit: String, offset:String, name:String = ""): List<Cloth>

    @Select(value = ["SELECT * FROM cloth \${vas};"])
    fun getData2(vas:String, limit: Int, offset: Int):List<Any>

    @Select(value = ["SELECT cl.cloth_id , cl.name, cl.description, cl.available, po.price," +
            "cc.name as color, fc.name as formality, sc.name as size, tc.name as type  from "+
            " cloth cl,"+
            " product_order po,"+
            " product p,"+
            " color_cloth cc,"+
            " formality_cloth fc,  "+
            " size_cloth sc,"+
            " type_cloth tc"+
            " where cl.cloth_id = po.cloth_id"+
            " and p.product_id = po.product_id"+
            " and cc.color_id = p.color_cloth_id"+
            " and fc.formality_id = p.formality_cloth_id"+
            " and sc.size_id = p.size_cloth_id"+
            " and tc.type_id = p.type_id"+
            " and cl.name LIKE '\${name}%'"+
            " \${commands}"+
            " ORDER BY cl.cloth_id DESC"+
            " LIMIT \${limit}"+
            " OFFSET \${offset};"])
    fun getDesigns(commands:String ,limit: String, offset:String, name:String = ""): List<Any>


    @Select(value = ["SELECT im.image_id, im.available, im.uuidFile, im.filename, im.title" +
            " im.description from image im" +
            " WHERE im.cloth_id = \${idCloth} "])
    fun getClothImages(idCloth: Int): List<Image>

    @Select( value = ["Select cl.cloth_id, cl.name, cl.description, cl.user_id , cl.available " +
            "from cloth cl "+
            " ORDER BY cl.cloth_id DESC " +
            " LIMIT \${limit} " +
            " OFFSET \${offset};"])
    fun getDesigns(limit: String, offset: String):List<Design>

    @Select( value = [
        "select cl.cloth_id, cl.name, cl.description, cl.user_id , cl.available from " +
                "type_cloth tc, type_prod tp, cloth cl, cloth_category clct, category ct " +
                "where cl.cloth_id = clct.cloth_id " +
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


}