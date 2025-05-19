package com.sky.mapper.admin.sql;


import com.sky.dto.OrdersPageQueryDTO;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class OrdersMapperProvider {


  public String selectAllStatusCount2() {
    return """
        SELECT COALESCE
          ( SUM( CASE WHEN STATUS = 2 THEN 1 ELSE 0 END ), 0 ) AS waiting4_merchant_receive_orders,
          COALESCE ( SUM( CASE WHEN STATUS = 3 THEN 1 ELSE 0 END ), 0 ) AS waiting4_rider_receive_orders,
          COALESCE ( SUM( CASE WHEN STATUS = 4 THEN 1 ELSE 0 END ), 0 ) AS to_be_delivery_on_progress_orders,
          COALESCE ( SUM( CASE WHEN STATUS = 5 THEN 1 ELSE 0 END ), 0 ) AS to_be_arrived_orders,
          COALESCE ( SUM( CASE WHEN STATUS = 6 THEN 1 ELSE 0 END ), 0 ) AS completed_orders,
          COALESCE ( SUM( CASE WHEN STATUS = 7 THEN 1 ELSE 0 END ), 0 ) AS canceled_orders
        FROM
          orders;
        """;
  }

  public String selectOrdersList(Map<String, Object> params) {
    OrdersPageQueryDTO query = (OrdersPageQueryDTO) params.get("query");

    return new SQL() {{
      SELECT("*");
      FROM("orders");
      buildWhereClause(this, query);
      ORDER_BY("order_time DESC");
      LEFT_OUTER_JOIN("tb_trade_order ON orders.number = tb_trade_order.order_number");
      LEFT_OUTER_JOIN("tb_courier ON tb_trade_order.courier_id = tb_courier.id");
    }}.toString();
  }

  public void buildWhereClause(SQL sql, OrdersPageQueryDTO query) {
    if (query != null) {
      if (query.getStatus() != null) {
        sql.WHERE("orders.status = #{query.status}");
      }
      if (query.getNumber() != null) {
        sql.WHERE("orders.number LIKE CONCAT('%', #{query.number}, '%')");
      }
      if (query.getPhone() != null) {
        sql.WHERE("orders.phone LIKE CONCAT('%', #{query.phone}, '%')");
      }
      if (query.getBeginTime() != null && query.getEndTime() != null) {
        sql.WHERE("orders.order_time BETWEEN #{query.beginTime} AND #{query.endTime}");
      }
      if (query.getUserId() != null) {
        sql.WHERE("orders.user_id = #{query.userId}");
      }
      if (query.getPayStatus() != null) {
        sql.WHERE("pay_status = #{query.payStatus}");
      }
    }
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