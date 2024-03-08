package com.zyaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hostArticleList();
}
