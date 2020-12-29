package com.peppers.exam.entity.label;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.peppers.exam.common.config.SpringContextUtils;
import com.peppers.exam.enums.LabelTypeEnum;
import com.peppers.exam.utils.SnowflakeIDGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class TreeLabel<C extends TreeLabel<C>> extends Label{
    @Enumerated(EnumType.ORDINAL)
    @Column(name="type",length = 3)
    private LabelTypeEnum type;
    /**
     * 备注
     */
    private String remark;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parent",targetEntity = TreeLabel.class)
    @JsonManagedReference
    private List<C>  children = new ArrayList<>();

    @ManyToOne(targetEntity = TreeLabel.class)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private TreeLabel<C> parent;

    /**
     * ID路径
     */
    private String path;

    /**
     *
     * 1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
     *
     * 2）transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
     *
     * 3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。*/
    @Transient
    private transient SnowflakeIDGenerator idGenerator;

    /**
     * 添加子节点
     * @param child 子节点
     */
    public void addChildren(C child){
        getChildren().add(child);
        child.setParent(this);
        child.setId((Long)SnowflakeIDGenerator.generateId());
        if(getPath()!=null){
            child.setPath(getPath()+"#"+child.getId());
        }else{
            child.setPath(String.valueOf(child.getId()));
        }
    }
}
