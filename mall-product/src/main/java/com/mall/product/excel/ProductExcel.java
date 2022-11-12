package com.mall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;

@Data
//头背景设置成红色 IndexedColors.RED.getIndex()
@HeadStyle(fillPatternType = FillPatternType.FINE_DOTS, fillForegroundColor = 40)
//头字体设置成20
@HeadFontStyle(fontHeightInPoints = 13)
//内容的背景设置成绿色 IndexedColors.GREEN.getIndex()
//@ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 17)
//内容字体设置成20
@ContentFontStyle(fontHeightInPoints = 12)
public class ProductExcel {

    /**
     * 商品编码
     */
    @ExcelProperty(index = 0,value = "商品编码")
    @ColumnWidth(15)
    private String productNumber;

    /**
     * 商品名称
     */
    @ExcelProperty(index = 1,value = "商品名称")
    @ColumnWidth(24)
    private String productName;

    /**
     * 商品大类
     */
    @ExcelProperty(index = 2,value = "商品大类")
    @ColumnWidth(12)
    private String categoryClass1;

    /**
     * 商品中类
     */
    @ExcelProperty(index = 3,value = "商品中类")
    @ColumnWidth(12)
    private String categoryClass2;

    /**
     * 商品小类
     */
    @ExcelProperty(index = 4,value = "商品小类")
    @ColumnWidth(12)
    private String categoryClass3;

    /**
     * 商品细类
     */
    @ExcelProperty(index = 5,value = "商品细类")
    @ColumnWidth(15)
    private String categoryClass4;

    /**
     * 季节编码
     */
    @ExcelProperty(index = 6,value = "年份编码")
    @ColumnWidth(12)
    private String yearCode;

    /**
     * 季节编码
     */
    @ExcelProperty(index = 7,value = "季节编码")
    @ColumnWidth(12)
    private String seasonCode;

    /**
     * 商标code
     */
    @ExcelProperty(index = 8,value = "商标code")
    @ColumnWidth(12)
    private String trademarkCode;

    /**
     * 商标名称
     */
    @ExcelProperty(index = 9,value = "商标名称")
    @ColumnWidth(12)
    private String trademarkName;

    /**
     * 供应商code
     */
    @ExcelProperty(index = 10,value = "供应商code")
    @ColumnWidth(12)
    private String supplierCode;

    /**
     * 供应商名称
     */
    @ExcelProperty(index = 11,value = "供应商名称")
    @ColumnWidth(12)
    private String supplierName;



}
