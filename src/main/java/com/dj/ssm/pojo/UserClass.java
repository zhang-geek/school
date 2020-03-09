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
 * @date 2020/3/9 14:56
 */
@TableName("class_school")
@Data
@Accessors(chain = true)
public class UserClass {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer classNum;

    /**
     * 班级状态：0未开班  1已开班  2已结课
     */
    private Integer classStatus;

    @TableField(exist = false)
    private String classStatusShow;

    public String getClassStatusShow() {
        if (classStatus == null) {
            return "暂无";
        }
        if (classStatus == 0) {
            return "未开班";
        } else if (classStatus == 1) {
            return "已开班";
        } else if (classStatus == 2) {
            return "已结课";
        }
        return "暂无";
    }
}
