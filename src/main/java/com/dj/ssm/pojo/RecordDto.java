package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("record_school")
@Data
public class RecordDto {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer recordId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 消费金额
     */
    private String recordMoney;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 消费时间
     */
    private Date recordTime;
}
