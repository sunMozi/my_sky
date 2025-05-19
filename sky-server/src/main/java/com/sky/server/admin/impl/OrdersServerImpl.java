package com.sky.server.admin.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.mapper.admin.OrdersMapper;
import com.sky.result.PageResult;
import com.sky.server.admin.OrdersServer;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/5/18 14:39
 * @description
 */
@Service
public class OrdersServerImpl implements OrdersServer {

  @Resource
  private OrdersMapper ordersMapper;


  @Override
  public PageResult<OrderVO> conditionSearch(OrdersPageQueryDTO query) {
    PageHelper.startPage(query.getPage(), query.getPageSize());
    List<OrderVO> ordersList = ordersMapper.selectOrdersList(query);
    Page<OrderVO> page = (Page<OrderVO>) ordersList;
    return new PageResult<>(page.getTotal(), page.getResult());
  }

  @Override
  public OrderStatisticsVO statistics() {
    return ordersMapper.selectAllStatusCount2();
  }
}