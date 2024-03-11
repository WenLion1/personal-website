package com.zyaire.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-03-10 16:04:52
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();
}
