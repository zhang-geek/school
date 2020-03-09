package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class DormSchool {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer dormNumber;

    private Integer dormStatus;

    private Date createTime;

    private Integer isDel;
}
