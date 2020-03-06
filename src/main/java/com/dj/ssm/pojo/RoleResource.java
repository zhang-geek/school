package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 22:19
 */
@Data
@Accessors(chain = true)
@TableName("role_resource_school")
public class RoleResource {
    private Integer roleId;
    private Integer resourceId;
}
