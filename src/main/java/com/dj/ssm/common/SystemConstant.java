package com.dj.ssm.common;

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
     * 校园卡状态 正常使用
     */
    public static final Integer CARD_STATUS_USE = 0;
    /**
     * 卡费
     */
    public static final BigDecimal CARD_MONEY_TWO = BigDecimal.valueOf(2.00);
}
