package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/30 19:47
 */
@Data
@Accessors(chain = true)
@TableName("resource_school")
public class Resource {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String resourceName;
    private String url;
    private Integer parentId;
    private Integer isDel;
    @TableField(exist = false)
    private Boolean checked = false;
    @TableField(exist = false)
    private String target = "right";

    /**
     * 资源类型 1：url 2：按钮
     */
    private Integer resourceType;
}
