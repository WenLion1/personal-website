package com.zyaire.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyaire.domain.entity.Article;
import com.zyaire.mapper.ArticleMapper;
import com.zyaire.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
