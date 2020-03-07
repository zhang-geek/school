package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("book")
public class Book {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String bookName;
    private String author;
    private Integer type;
    /**
     * 0：上架 1：下架
     */
    private Integer status;
    private Integer count;
    private Integer userId;
    private Integer isDel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shelfTime;

    @TableField(exist = false)
    private String shelfTimeShow;

    public String getShelfTimeShow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(shelfTime);
    }

    @TableField(exist = false)
    private String typeName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date topTime;




}
