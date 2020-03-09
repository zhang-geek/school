package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("borrow_car")
public class Borrow {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer bookId;
    private String author;
    private Integer userId;
    private Integer number;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date borrowTime;
    /**
     * 还书时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date repayTime;

    @TableField(exist = false)
    private String bookName;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String statusShow;
    private Integer type;
    private Integer isDel;
    /**
     * 支付方式：16：支付宝 17：微信 18：银行卡
     */
    private Integer pay;
    @TableField(exist = false)
    private String payShow;
}
