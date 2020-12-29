package com.peppers.exam.view.vo.label;

import com.peppers.exam.view.dto.label.TreeLabelDTO;

import java.util.List;


public class TreeLabelVO<T> extends TreeLabelDTO {

    private List<TreeLabelVO> children;
    private TreeLabelVO parent;

    /**
     * 备注
     */
    private String remark;

    public List<TreeLabelVO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeLabelVO> children) {
        this.children = children;
    }

    public TreeLabelVO getParent() {
        return parent;
    }

    public void setParent(TreeLabelVO parent) {
        this.parent = parent;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TypesLabelVO{" +
                "type=" + getType() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", enabled=" + getEnabled() +
                ", code='" + getCode() + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        if(getId()==null){
            return Long.valueOf(System.currentTimeMillis()).hashCode();
        }
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
