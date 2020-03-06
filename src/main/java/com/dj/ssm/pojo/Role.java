package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/30 19:46
 */
@Data
@Accessors(chain = true)
@TableName("role_school")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String roleName;
    private Integer isDel;
}
