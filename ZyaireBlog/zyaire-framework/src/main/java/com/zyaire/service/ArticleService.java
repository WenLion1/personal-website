package com.zyaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.dto.ArticleDto;
import com.zyaire.domain.dto.ArticleListDto;
import com.zyaire.domain.entity.Article;
import com.zyaire.domain.vo.UpdateArticleDetailVo;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Integer categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(ArticleDto articleDto);

    ResponseResult pageArticleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto);

    ResponseResult updateArticleDetail(Long id);

    ResponseResult updateArticle(UpdateArticleDetailVo updateArticleDetailVo);

    ResponseResult deleteArticle(Long id);
}
