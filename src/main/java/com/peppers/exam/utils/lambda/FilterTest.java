package com.peppers.exam.utils.lambda;

import com.peppers.exam.entity.test.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author peppers
 * @description
 * @since 2021/1/19
 **/
public class FilterTest {


    /**
     * 通过 filter 方法实现查询所有订单中最近半年金额大于 40 的订单，通过连续叠加 filter 方法进行多次条件过滤：
     * */
    public void testOrder(){

        List<Order> orders = new ArrayList<>();
        orders.stream()
                /**过滤null值*/
                .filter(Objects::isNull)
                //最近半年的订单
                .filter(order->order.getPlacedAt().isAfter(LocalDateTime.now().minusMonths(6)))
                // 金额大于40 的 订单
                .filter(order -> order.getTotalPrice() > 40)
        .forEach(System.out::println);
    }
}
