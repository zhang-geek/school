package com.dj.ssm.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 借书记录表 chengf
 */
@Data
@Accessors(chain = true)
@TableName("borrow_car")
public class Borrow {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 外键 书本ID
     */
    private Integer bookId;
    /**
     * 外键 用户ID
     */
    private Integer userId;
    /**
     * 作者
     */
    private String author;
    /**
     * 借书数量
     */
    private Integer number;
    /**
     * 书本类型
     */
    private Integer type;
    /**
     * 缴费金额
     */
    private BigDecimal pay;
    /**
     * 借书时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date borrowTime;
    /**
     * 还书时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date repayTime;
    /**
     * 0 未删除 1 已删除
     */
    private Integer isDel;

    /**
     * 不属于此表字段 书名
     */
    @TableField(exist = false)
    private String bookName;
    /**
     * 不属于此表字段 用户名
     */
    @TableField(exist = false)
    private String userName;
    /**
     * 不属于此表字段 书本类型
     */
    @TableField(exist = false)
    private String typeName;
    /**
     * 不属于此表字段 逾期状态
     */
    @TableField(exist = false)
    private String statusShow;
    /**
     * 不属于此表字段 缴费金额
     */
    @TableField(exist = false)
    private String payShow;
    /**
     * 不属于此表字段 校园卡余额
     */
    @TableField(exist = false)
    private BigDecimal cardMoney;
    /**
     * 不属于此表字段 罚款
     */
    @TableField(exist = false)
    private BigDecimal penalty;
    /**
     * 不属于此表字段 逾期天数
     */
    @TableField(exist = false)
    private String days;
    /**
     * 不属于此表字段 剩余天数
     */
    @TableField(exist = false)
    private String surplus;

}
