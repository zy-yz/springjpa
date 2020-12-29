package com.peppers.exam.entity.permission;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.peppers.exam.entity.baseeneity.BaseEntity;
import com.peppers.exam.utils.SnowflakeIDGenerator;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author peppers
 * @description
 * @since 2020/12/25
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"children","parent","permissionRolesRelation"})
public class SysPermission<C extends SysPermission> extends BaseEntity {

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconcls;

    private Boolean keepalive;

    private Boolean requireauth;

    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parent",targetEntity = SysPermission.class)
    @JsonManagedReference
    private List<C> children = new ArrayList<>();

    @ManyToOne(targetEntity = SysPermission.class)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private SysPermission<C> parent;

    @Transient
    private transient SnowflakeIDGenerator idGenerator;

    /**
     *
     * 通过中间表*/
    @OneToMany(mappedBy = "sysPermission")
    private List<SysPermissionRole> permissionRolesRelation = new ArrayList<>();
}
