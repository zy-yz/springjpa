package com.peppers.exam.entity.menu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.peppers.exam.entity.baseeneity.BaseEntity;
import com.peppers.exam.utils.SnowflakeIDGenerator;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author peppers
 * @description 菜单表
 * @since 2020/12/25
 **/
@Data
@Entity
@ToString(exclude = {"children","parent","menuRelation"})
public class SysMenu<C extends SysMenu> extends BaseEntity implements Cloneable, Serializable {


    /**
     * @Description: 浅克隆
     * @Param: []
     * @return: java.lang.Object
     **/
    @Override
    public Object clone() {
        SysMenu sysMenu;
        try {
            sysMenu = (SysMenu) super.clone();
            return sysMenu;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 菜单类型（1：左侧主菜单；2：页面中的按钮；3：页面中标签）
     */
    private Integer menuType;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单英文名称
     */
    private String titleEn;

    /**
     * 菜单图标
     */
    private String iconPic;

    /**
     * vue组件根路径
     */
    private String path;

    /**
     * vue的组件名
     */
    private String component;

    /**
     * 按钮id（页面级别唯一)
     */
    private String elementId;

    /**
     * 是否有效（默认1：有效；0：无效；）
     */
    private Boolean enabled;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 菜单的排序
     */
    private Integer sortOrder;

    /**
     * 角色选择（前端使用）
     */
    private Boolean onChoose;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parent",targetEntity = SysMenu.class)
    @JsonManagedReference
    private List<C> children = new ArrayList<>();

    @ManyToOne(targetEntity = SysMenu.class)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private SysMenu<C> parent;

    @Transient
    private transient SnowflakeIDGenerator idGenerator;

    /**
     *
     * 通过中间表*/
    @OneToMany(mappedBy = "menu")
    private List<SysMenuRole> menuRelation = new ArrayList<>();

//    public  <D extends CostFeeTemplate<D>> void generateCostFeeFromTemplate(List<CostFeeTemplate> templates, CostAnalysis entity){
//        templates.forEach(t->{
//            CostFee costFee = new CostFee();
//            costFee.setName(t.getName());
//            costFee.setSortNum(t.getSortNum());
//            costFee.setType(COST_FEE_TYPE);
//            getCostFees().add(costFee);
//            costFee.setCostAnalysis(entity);
//            if(!t.getChildren().isEmpty()){
//                generateCostFeeChildrenFromTemplate(costFee,t.getChildren());
//            }
//        });
//        entity.setCostFees(costFees);
//    }
//
//    private <D extends CostFeeTemplate<D>> void generateCostFeeChildrenFromTemplate(CostFee parent, List<CostFeeTemplate> templates){
//        templates.forEach(t->{
//            CostFee costFee = new CostFee();
//            costFee.setName(t.getName());
//            parent.getChildren().add(costFee);
//            costFee.setSortNum(t.getSortNum());
//            costFee.setType(COST_FEE_TYPE);
//            costFee.setParent(parent);
//            if(!t.getChildren().isEmpty()){
//                generateCostFeeChildrenFromTemplate(costFee,t.getChildren());
//            }
//        });
//    }

}
