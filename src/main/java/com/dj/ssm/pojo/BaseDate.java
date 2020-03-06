package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("base_date")
public class BaseDate {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String baseName;

    private Integer parenrId;

    private Integer isDel;

}
