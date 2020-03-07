package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("shop")
public class Shop {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String shopName;

    private Double shopPrice;

    private Integer shopStatus;

    private Integer isDel;

    private Integer baseDataId;

    @TableField(exist = false)
    private String baseName;
}
