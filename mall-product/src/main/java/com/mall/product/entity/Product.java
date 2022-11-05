package com.mall.product.entity;

import java.util.Date;
import lombok.Data;

/**
    * 商品表
    */
@Data
public class Product {
    /**
    * 主键ID
    */
    private String id;

    /**
    * 商品编码
    */
    private String productNumber;

    /**
    * 商品名称
    */
    private String productName;

    /**
    * 商品大类
    */
    private String categoryClass1;

    /**
    * 商品中类
    */
    private String categoryClass2;

    /**
    * 商品小类
    */
    private String categoryClass3;

    /**
    * 商品细类
    */
    private String categoryClass4;

    /**
    * 年度编码
    */
    private String yearCode;

    /**
    * 季节编码
    */
    private String seasonCode;

    /**
    * 商标code
    */
    private String trademarkCode;

    /**
    * 商标名称
    */
    private String trademarkName;

    /**
    * 供应商code
    */
    private String supplierCode;

    /**
    * 供应商名称
    */
    private String supplierName;

    /**
    * 商品是否有效
    */
    private String isvalid;

    /**
    * SAP更新时间
    */
    private String sapDateUpdated;

    /**
    * 备用1
    */
    private String remark1;

    /**
    * 备用2
    */
    private String remark2;

    /**
    * 备用3
    */
    private String remark3;

    /**
    * 备用4
    */
    private String remark4;

    /**
    * 备用5
    */
    private String remark5;

    /**
    * 备用6
    */
    private String remark6;

    /**
    * 备用7
    */
    private String remark7;

    /**
    * 备用8
    */
    private String remark8;

    /**
    * 备用9
    */
    private String remark9;

    /**
    * 备用10
    */
    private String remark10;

    /**
    * 创建者
    */
    private Date dateCreated;

    /**
    * 创建时间
    */
    private String createdBy;

    /**
    * 修改者
    */
    private String updatedBy;

    /**
    * 修改时间
    */
    private Date dateUpdated;
}