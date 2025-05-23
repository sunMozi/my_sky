package com.sky.controller.admin;


import com.sky.dto.OrdersPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.server.admin.OrdersServer;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/18 14:36
 * @description
 */

@RestController
@RequestMapping("/admin/order")
@Tag(name = "订单管理")
public class OrdersController {

  @Resource
  private OrdersServer ordersServer;

  @GetMapping("statistics")
  public Result<OrderStatisticsVO> statistics() {
    return Result.success(ordersServer.statistics());
  }


  @GetMapping("conditionSearch")
  public Result<PageResult<OrderVO>> conditionSearch(OrdersPageQueryDTO query) {
    return Result.success(ordersServer.conditionSearch(query));
  }


}