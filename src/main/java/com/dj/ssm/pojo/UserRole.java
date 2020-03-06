package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 22:18
 */
@Data
@Accessors(chain = true)
@TableName("user_role_school")
public class UserRole {
    private Integer userId;
    private Integer roleId;
}
