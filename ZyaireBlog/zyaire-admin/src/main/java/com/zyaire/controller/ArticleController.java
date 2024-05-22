package com.zyaire.controller;

import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.dto.ArticleDto;
import com.zyaire.domain.dto.ArticleListDto;
import com.zyaire.domain.vo.UpdateArticleDetailVo;
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

    @GetMapping("{id}")
    public ResponseResult updateArticleDetail(@PathVariable("id") Long id){
        return articleService.updateArticleDetail(id);
    }

    @PutMapping
    public ResponseResult updateArticle(@RequestBody UpdateArticleDetailVo updateArticleDetailVo){
        return articleService.updateArticle(updateArticleDetailVo);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteArticle(@PathVariable("id") Long id){
        return articleService.deleteArticle(id);
    }
}
