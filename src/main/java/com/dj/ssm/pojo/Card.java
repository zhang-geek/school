package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 校园卡的pojo
 * @author zzy
 */
@TableName("card_school")
@Data
@Accessors(chain = true)
public class Card {

    @TableId(type = IdType.AUTO )
    private Integer id;
    /**
     * 校园卡卡号
     */
    private String cardNumber;
    /**
     * 用户ID
     */
    private Integer userId;

    @TableField(exist = false)
    private String userName;
    /**
     * 校园卡余额
     */
    private BigDecimal cardMoney;
    /**
     * 校园卡状态 0正常 1挂失
     */
    private Integer cardStatus;
    /**
     * 校园卡办理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    /**
     * 校园卡删除状态 0正常 1删除
     */
    private Integer isDel;

}
