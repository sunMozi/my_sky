package com.sky.mapper.admin;


import com.sky.dto.OrdersPageQueryDTO;
import com.sky.mapper.admin.sql.OrdersMapperProvider;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author moZiA
 * @date 2025/5/18 14:42
 * @description
 */
@Mapper
public interface OrdersMapper {

  @SelectProvider(type = OrdersMapperProvider.class, method = "selectOrdersList")
  List<OrderVO> selectOrdersList(@Param("query") OrdersPageQueryDTO query);

  @SelectProvider(type = OrdersMapperProvider.class, method = "selectAllStatusCount2")
  OrderStatisticsVO selectAllStatusCount2();
}