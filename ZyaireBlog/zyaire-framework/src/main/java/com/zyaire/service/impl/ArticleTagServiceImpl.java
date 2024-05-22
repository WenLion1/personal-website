package com.zyaire.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyaire.constants.SystemConstants;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.Article;
import com.zyaire.domain.entity.ArticleTag;
import com.zyaire.domain.entity.Category;
import com.zyaire.domain.vo.CategoryVo;
import com.zyaire.mapper.ArticleTagMapper;
import com.zyaire.service.ArticleTagService;
import com.zyaire.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2024-04-23 20:22:34
 */
@Service("articleTagService")

public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public List<Long> getTagIdList(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, id);
        List<ArticleTag> list = articleTagService.list(queryWrapper);
        List<Long> TagId = list.stream()
                .map(ArticleTag::getTagId)
                .collect(Collectors.toList());

        return TagId;
    }

    @Override
    public void updateArticleTag(List<Long> tags, Long articleId) {
        List<Long> tagIdList = articleTagService.getTagIdList(articleId);

        List<Long> tagToAdd = tags.stream()
                .filter(tag -> !tagIdList.contains(tag))
                .collect(Collectors.toList());

        List<Long> tagToRemove = tagIdList.stream()
                .filter(tag -> !tags.contains(tag))
                .collect(Collectors.toList());

        for (Long tagId : tagToAdd) {
            articleTagService.save(new ArticleTag(articleId, tagId));
        }

        for (Long tagId : tagToRemove) {
            getBaseMapper().deleteByArticleIdAndTagId(articleId, tagId);
        }
    }
}
