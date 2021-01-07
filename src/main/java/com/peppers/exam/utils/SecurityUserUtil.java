package com.peppers.exam.utils;

import com.peppers.exam.entity.user.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author peppers
 * @description 获取用户
 * @since 2021/1/7
 **/
public class SecurityUserUtil {

    /**
     * @Description      从认证中心里面获取当前用户权限信息
     * @param
     * @return com.peppers.exam.entity.user.SysUser
     */
    public static SysUser getCurrentUser() {
        return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * @Description      获取当前使用者的编号
     * @param
     * @return java.lang.Long
     */
    public static Long getCurrentUserId() {
        SysUser securitySysUser = getCurrentUser();
        if (null == securitySysUser) {
            return null;
        }
        return securitySysUser.getId();
    }
}
