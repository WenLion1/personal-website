package com.zyaire.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyaire.domain.entity.ArticleTag;
import org.apache.ibatis.annotations.Param;

/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-23 20:23:39
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    void deleteByArticleIdAndTagId(@Param("articleId") Long articleId, @Param("tagId") Long tagId);
}
