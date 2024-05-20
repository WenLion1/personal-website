package com.zyaire.controller;

import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.dto.ArticleDto;
import com.zyaire.domain.dto.ArticleListDto;
import com.zyaire.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody ArticleDto articleDto){
        return articleService.addArticle(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, ArticleListDto articleListDto){
        return articleService.pageArticleList(pageNum, pageSize, articleListDto);
    }
}
