package com.peppers.exam.label;

import com.peppers.exam.entity.label.TypesLabel;
import com.peppers.exam.repository.label.TypesLabelJpaRepository;
import com.peppers.exam.service.label.TypesLabelService;
import com.peppers.exam.view.dto.label.TypesLabelDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author peppers
 * @description 标签类单元测试
 * @since 2021/1/5
 **/
@DataJpaTest
public class LabelRepositoryTest {

    @Resource
    private TypesLabelJpaRepository typesLabelRepository;

    @Resource
    private TypesLabelService typesLabelService;

    @Test
    public  void testSave() {
        TypesLabel typesLabel = new TypesLabel();
        TypesLabelDTO dto = new TypesLabelDTO();
        typesLabel.setName("张三");
        typesLabelRepository.save(typesLabel);
        typesLabelService.create(dto);
        List<TypesLabel> address1 = typesLabelRepository.findAll();
        address1.stream().forEach(address2 -> System.out.println(address2));
    }

}
