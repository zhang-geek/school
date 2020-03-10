package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@TableName("order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer shopId;

    private Integer userId;

    private BigDecimal shopPrice;

    private String orderNum;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String shopName;
}
