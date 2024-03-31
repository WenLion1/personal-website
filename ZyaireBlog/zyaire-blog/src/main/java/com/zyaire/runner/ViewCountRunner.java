package com.zyaire.runner;

import com.zyaire.domain.entity.Article;
import com.zyaire.mapper.ArticleMapper;
import com.zyaire.service.ArticleService;
import com.zyaire.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream().collect(Collectors.toMap(article -> article.getId().toString(), article -> {
            return article.getViewCount().intValue();
        }));

        redisCache.setCacheMap("article:viewCount", viewCountMap);
    }
}
