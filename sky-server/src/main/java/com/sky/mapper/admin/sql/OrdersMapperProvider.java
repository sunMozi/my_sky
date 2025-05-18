package com.sky.mapper.admin.sql;


import com.sky.dto.OrdersPageQueryDTO;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class OrdersMapperProvider {


  public String selectAllStatusCount2() {
    return new SQL() {{

    }}.toString();
  }

  public String selectOrdersList(Map<String, Object> params) {
    OrdersPageQueryDTO query = (OrdersPageQueryDTO) params.get("query");

    return new SQL() {{
      String subQuery = buildSubQuery(query);

      // 主查询
      SELECT("*");
      FROM("orders");
    }}.toString();
  }

  private String buildSubQuery(OrdersPageQueryDTO query) {
    return new SQL() {{
      SELECT("*");
      FROM("orders");
      if (query.getNumber() != null && !query.getNumber().trim().isEmpty()) {
        WHERE("number = #{query.number}");
      }
      if (query.getStatus() != null) {
        WHERE("status = #{query.status}");
      }
      if (query.getPhone() != null && !query.getPhone().trim().isEmpty()) {
        WHERE("phone = #{query.phone}");
      }
      if (query.getBeginTime() != null) {
        WHERE("order_time >= #{query.beginTime}");
      }
      if (query.getEndTime() != null) {
        WHERE("order_time <= #{query.endTime}");
      }
    }}.toString();
  }
}