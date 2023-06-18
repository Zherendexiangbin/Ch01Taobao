package com.example.ch01taobao.service.order;

import com.example.ch01taobao.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getALl();

    Order getOne(Integer orderId);

    // 保存订单到服务器
    boolean save(Order order);
}
