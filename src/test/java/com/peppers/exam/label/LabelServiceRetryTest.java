package com.peppers.exam.label;

import com.peppers.exam.ExamApplication;
import com.peppers.exam.config.RetryConfiguration;
import com.peppers.exam.entity.label.TypesLabel;
import com.peppers.exam.repository.label.TypesLabelJpaRepository;
import com.peppers.exam.service.label.TypesLabelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author peppers
 * @description
 * @since 2020/12/29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExamApplication.class)
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@ComponentScan(basePackageClasses= {TypesLabelService.class,TypesLabelJpaRepository.class})
@Import(RetryConfiguration.class)
public class LabelServiceRetryTest {


    @Autowired
    private TypesLabelService typesLabelService;

    @Autowired
    private TypesLabelJpaRepository typesLabelJpaRepository;


    @Test
    @Rollback(false)
    @Transactional(propagation = Propagation.NEVER)
    public void testRetryable() {
        TypesLabel typesLabel = new TypesLabel();
        typesLabel.setCode("123");
        //新增一条数据
        TypesLabel typesLabel1 = typesLabelJpaRepository.saveAndFlush(typesLabel);
//        new Thread(() -> typesLabelService.calculate(1L)).start();
//        try {
//            Thread.sleep(10L);//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //如果两个线程同时执行会发生乐观锁异常；
//        Exception exception = Assertions.assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
//            typesLabelService.calculate(1L);
//            //模拟多线程执行两次
//        });
//        System.out.println(exception);


    }
}
