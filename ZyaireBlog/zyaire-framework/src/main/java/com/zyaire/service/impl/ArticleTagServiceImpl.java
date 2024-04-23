package com.zyaire.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyaire.domain.entity.ArticleTag;
import com.zyaire.mapper.ArticleTagMapper;
import com.zyaire.service.ArticleTagService;
import org.springframework.stereotype.Service;
/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2024-04-23 20:22:34
 */
@Service("articleTagService")

public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
