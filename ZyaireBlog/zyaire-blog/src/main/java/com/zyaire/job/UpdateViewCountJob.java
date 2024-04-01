package com.zyaire.job;

import com.zyaire.domain.entity.Article;
import com.zyaire.service.ArticleService;
import com.zyaire.utils.RedisCache;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/55 * * * * ?")
    public void updateViewCount(){
        Map<String, Integer> cacheMap = redisCache.getCacheMap("article:viewCount");
        List<Article> collect = cacheMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        articleService.updateBatchById(collect);
    }
}
