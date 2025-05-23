package com.sky.server.user;


import com.sky.entiry.Category;
import com.sky.result.Result;
import java.util.List;

/**
 * @author moZiA
 * @date 2025/5/23 14:22
 * @description
 */
public interface CategoryService {

  List<Category> list(Integer type);
}