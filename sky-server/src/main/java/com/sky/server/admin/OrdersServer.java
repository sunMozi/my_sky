package com.sky.server.admin;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entiry.Orders;
import com.sky.result.PageResult;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;

/**
 * @author MoZi
 * @createTime 2025/5/18 14:39
 */
public interface OrdersServer {

  PageResult<OrderVO> conditionSearch(OrdersPageQueryDTO query);

  OrderStatisticsVO statistics();
}
