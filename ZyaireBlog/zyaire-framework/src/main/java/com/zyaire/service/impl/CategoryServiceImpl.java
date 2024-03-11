package com.zyaire.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyaire.constants.SystemConstants;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.Article;
import com.zyaire.domain.entity.Category;
import com.zyaire.domain.vo.CategoryVo;
import com.zyaire.mapper.CategoryMapper;
import com.zyaire.service.ArticleService;
import com.zyaire.service.CategoryService;
import com.zyaire.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-03-10 16:04:52
 */
@Service("categoryService")

public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);

        Set<Long> categoryIds = articleList.stream()
                                .map(Article::getCategoryId)
                                .collect(Collectors.toSet());

        List<Category> categories = listByIds(categoryIds);

        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        List<CategoryVo> CategoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(CategoryVos);
    }
}
