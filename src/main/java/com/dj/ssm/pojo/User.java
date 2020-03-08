package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/29 20:50
 */
@TableName("user_school")
@Data
@Accessors(chain = true)
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String userPhone;
    private String userEmail;
    /**
     * 1:管理员， 2：普通用户
     */
    @TableField(exist = false)
    private Integer userRole;
    private Integer userStatus;
    private Date createTime;
    private Date lastLoginTime;
    private Date updateTime;
    /**
     * 0:未删除 1：已删除
     */
    private Integer isDel;
    @TableField(exist = false)
    private String verifyCode;
    private String salt;
    /**
     * 0:未认证，1：已认证
     */
    private Integer isVerify;
    @TableField(exist = false)
    private String roleName;
}
