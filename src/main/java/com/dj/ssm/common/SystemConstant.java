package com.dj.ssm.common;

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
     * 用户状态：未删除
     */
    public static final Integer IS_NOT_DEL = 0;

    /**
     * 用户状态：已删除
     */
    public static final Integer IS_DEL = 1;

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
}
