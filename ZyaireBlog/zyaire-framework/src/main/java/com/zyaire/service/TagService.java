package com.zyaire.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.dto.TagListDto;
import com.zyaire.domain.entity.Tag;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2024-04-08 19:51:23
 */
public interface TagService extends IService<Tag> {

    ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);
}
