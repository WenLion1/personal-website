package com.zyaire.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyaire.domain.entity.Tag;
import com.zyaire.mapper.TagMapper;
import com.zyaire.service.TagService;
import org.springframework.stereotype.Service;
/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2024-04-08 19:51:23
 */
@Service("tagService")

public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
