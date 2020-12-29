package com.peppers.exam.view.converter.label;


import com.peppers.exam.entity.label.TreeLabel;
import com.peppers.exam.entity.label.TypesLabel;
import com.peppers.exam.view.vo.label.TreeLabelVO;
import com.peppers.exam.view.vo.label.TypesLabelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class LabelConverter {
    public static TypesLabelVO toVO(TypesLabel typesLabel) {
        if (typesLabel == null) {
            return null;
        }
        TypesLabelVO typesLabelVO = new TypesLabelVO();
        BeanUtils.copyProperties(typesLabel,typesLabelVO);
        return typesLabelVO;
    }

    public static List<TypesLabelVO> toTypeVOS(List<TypesLabel> typesLabels){
        if (typesLabels == null){
            return null;
        }
        return typesLabels.stream().map(LabelConverter::toVO).collect(Collectors.toList());
    }

    public static TreeLabelVO toVO(TreeLabel treeTypesLabel){
        if(treeTypesLabel ==null){
            return null;
        }
        TreeLabelVO treeLabelVO = new TreeLabelVO();
        BeanUtils.copyProperties(treeTypesLabel,treeLabelVO);

        if (Optional.ofNullable(treeTypesLabel.getParent()).isPresent()) {
            treeLabelVO.setParentId(treeTypesLabel.getParent().getId());
        }
        if (!CollectionUtils.isEmpty(treeTypesLabel.getChildren())){
            treeLabelVO.setChildren(toTreeVOS(treeTypesLabel.getChildren()));
        }
        return treeLabelVO;
    }

    public static List<TreeLabelVO> toTreeVOS(List<TreeLabel> treeLabels){
        if (treeLabels == null){
            return null;
        }
        return treeLabels.stream().map(LabelConverter::toVO).collect(Collectors.toList());

    }

    public static TypesLabel toVO(TypesLabelVO typesLabel) {
        if (typesLabel == null) {
            return null;
        }
        TypesLabel typesLabelVO = new TypesLabel();
        BeanUtils.copyProperties(typesLabel,typesLabelVO);
        return typesLabelVO;
    }


    public static List<TypesLabel> toVOS(List<TypesLabelVO> command){
        if (command == null){
            return null;
        }
        return command.stream().map(LabelConverter::toVO).collect(Collectors.toList());
    }

    public static TreeLabel toVO(TreeLabelVO treeTypesLabel){
        if(treeTypesLabel ==null){
            return null;
        }
        TreeLabel treeLabel = new TreeLabel();
        BeanUtils.copyProperties(treeTypesLabel,treeLabel);

        return treeLabel;
    }

//    public static List<TreeLabel> toTreeVOS(List<TreeLabelVO> command){
//        if (command == null){
//            return null;
//        }
//        return command.stream().map(LabelConverter::toVO).collect(Collectors.toList());
//    }

}
