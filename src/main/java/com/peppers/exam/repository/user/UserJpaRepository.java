package com.peppers.exam.repository.user;

import com.peppers.exam.entity.user.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author peppers
 * @description
 * @since 2021/1/7
 **/
public interface UserJpaRepository extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor<SysUser>, UserRepository {
}
