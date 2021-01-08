package com.peppers.exam.view.converter.user;

import com.peppers.exam.entity.role.SysRole;
import com.peppers.exam.entity.roleuser.SysUserRole;
import com.peppers.exam.entity.user.SysUser;
import com.peppers.exam.view.vo.role.SysRoleVO;
import com.peppers.exam.view.vo.user.SysUserRoleVO;
import com.peppers.exam.view.vo.user.SysUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author peppers
 * @description 用户信息转换器
 * @since 2021/1/8
 **/
public class UserConverter {

    /**用户信息转换*/
    public static SysUserVO toVO(SysUser source) {
        if (source == null) {
            return null;
        }
        SysUserVO target = new SysUserVO();
        BeanUtils.copyProperties(source,target);
        if (!CollectionUtils.isEmpty(source.getUserRoleRelation())){
            toUserRoleVO(source.getUserRoleRelation());
        }
        return target;
    }


    /**中间表转换*/
    public static SysUserRoleVO toVO(SysUserRole source) {
        if (source == null) {
            return null;
        }
        SysUserRoleVO target = new SysUserRoleVO();
        BeanUtils.copyProperties(source,target);
        if (Optional.ofNullable(source.getRoleUser()).isPresent()){
            toVO(source.getRoleUser());
        }
        return target;
    }

    /**中间表转换*/
    public static List<SysUserRoleVO> toUserRoleVO(List<SysUserRole> sysUserRoles){
        if(sysUserRoles == null){
            return null;
        }
        return sysUserRoles.stream().map(UserConverter::toVO).collect(Collectors.toList());
    }

    /**角色表转换*/
    public static SysRoleVO toVO(SysRole source) {
        if (source == null) {
            return null;
        }
        SysRoleVO target = new SysRoleVO();
        BeanUtils.copyProperties(source,target);
        return target;
    }

    /**角色表转换*/
    public static List<SysRoleVO> toRoleVOS(List<SysRole> source){
        if (source == null){
            return null;
        }
        return source.stream().map(UserConverter::toVO).collect(Collectors.toList());
    }


}
