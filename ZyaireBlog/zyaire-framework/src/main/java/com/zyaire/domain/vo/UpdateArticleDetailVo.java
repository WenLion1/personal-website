package com.zyaire.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleDetailVo {
    private Long categoryId;
    private String content;
    private Long createBy;
    private Date createTime;
    private Integer delFlag;
    private Long id;
    private String isComment;
    private String isTop;
    private String status;
    private String summary;
    private List<Long> tags;
    private String thumbnail;
    private String title;
    private Long updateBy;
    private Date updateTime;
    private Long viewCount;
}
