package com.zyaire.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.LoginUser;
import com.zyaire.domain.vo.CommentVo;
import com.zyaire.domain.vo.PageVo;
import com.zyaire.exception.SystemException;
import com.zyaire.mapper.CommentMapper;
import com.zyaire.service.UserService;
import com.zyaire.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zyaire.domain.entity.Comment;
import com.zyaire.service.CommentService;
import org.springframework.util.StringUtils;
import com.zyaire.enums.AppHttpCodeEnum;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-03-15 20:03:44
 */
@Service("commentService")

public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, -1);

        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<CommentVo> commentVoList = toCommentList(page.getRecords());

        for (CommentVo commentVo : commentVoList) {
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVo> getChildren(Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Comment::getCreateTime);
        queryWrapper.eq(Comment::getRootId, id);

        List<Comment> list = list(queryWrapper);
        return toCommentList(list);
    }

    private List<CommentVo> toCommentList(List<Comment> list){
        List<CommentVo> vs = BeanCopyUtils.copyBeanList(list, CommentVo.class);

        for (CommentVo v : vs) {
            String nickName = userService.getById(v.getCreateBy()).getNickName();
            v.setUsername(nickName);

            if (v.getToCommentId() != -1){
                String toCommentUserNickName = userService.getById(v.getToCommentUserId()).getNickName();
                v.setToCommentUserName(toCommentUserNickName);
            }
        }

        return vs;
    }
}
