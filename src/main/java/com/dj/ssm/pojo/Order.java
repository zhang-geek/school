package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer shopId;

    private Integer userId;

    private Double shopPrice;

    private String orderNum;
}
