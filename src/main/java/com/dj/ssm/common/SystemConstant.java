package com.dj.ssm.common;

import com.dj.ssm.pojo.Book;

import java.math.BigDecimal;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/29 20:49
 */
public class SystemConstant {
    /**
     * 每页条数：3
     */
    public static final Integer PAGE_SIZE = 3;

    /**
     * 状态：未删除
     */
    public static final Integer IS_NOT_DEL = 0;

    /**
     * 状态：已删除
     */
    public static final Integer IS_DEL = 1;

    /**
     * 图书上架
     */
    public static final Integer BOOK_STATUS_0 = 0;

    /**
     * 商品置顶
     */
    public static final Integer SHOP_STICK = 0;

    /**
     * 图书下架
     */
    public static final Integer BOOK_STATUS_1 = 1;

    /**
     * 资源表里parentId位36的资源
     */
    public static final Integer RESOURCE_PARENT_ID_36 = 36;


    /**
     * session存放的resourceList的key
     */
    public static final String SESSION_USER_RESOURCES = "USER_RESOURCE";

    /**
     * session存放的user信息的key
     */
    public static final String SESSION_USER = "USER";

    /**
     * 资源类型1：url
     */
    public static final Integer RESOURCE_TYPE_URL = 1;
    /**
     * 借书记录表审核状态 0：未审核
     */
    public static final Integer BORROW_NOT_STATUS = 0;
    /**
     * 书本库存为0本时
     */
    public static final Integer COUNT_0 = 0;
    /**
     * 校园卡状态 正常使用
     */
    public static final Integer CARD_STATUS_USE = 0;
    /**
     * 校园卡状态 已挂失
     */
    public static final Integer CARD_STATUS_NOT_USE = 1;
    /**
     * 卡费
     */
    public static final BigDecimal CARD_MONEY_TWO = BigDecimal.valueOf(2.00);

    /**
     * 班级状态：请选择
     */
    public static final Integer CLASS_STATUS_INVALID = -1;
    /**
     * status：1 默认
     */
    public static final Integer USER_STATUS = 1;
    /**
     * 认证：未认证  0
     */
    public static final Integer ISVERIFY = 0;
    /**
     * 父级id支付方式：16：支付宝 17：微信 18：银行卡
     */
    public static final Integer PARENT_ID_15 = 15;
}
