package com.peppers.exam.utils.lambda;

import com.peppers.exam.entity.test.Order;
import com.peppers.exam.entity.test.OrderItem;
import com.peppers.exam.entity.test.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author peppers
 * @description
 * @since 2021/1/19
 **/
public class MapTest {

    public void TestMap(){

        //计算所有订单商品数量
        //通过两次遍历实现
        List<Order> orders = new ArrayList<>();

        LongAdder longAdder = new LongAdder();
        orders.stream().forEach(order ->
                order.getOrderItemList().forEach(orderItem -> longAdder.add(orderItem.getProductQuantity())));

        //使用两次mapToLong+sum方法实现
        assertThat(longAdder.longValue(), is(orders.stream().mapToLong(order ->
                order.getOrderItemList().stream()
                        .mapToLong(OrderItem::getProductQuantity).sum()).sum()));


        //把IntStream通过转换Stream<Project>
        System.out.println(IntStream.rangeClosed(1,10)
                .mapToObj(i->new Product((long)i, "product"+i, i*100.0))
                .collect(toList()));
    }


}
