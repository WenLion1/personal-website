package com.zyaire.utils;

import com.zyaire.domain.entity.Article;
import com.zyaire.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){

    }

    public static <V>V copyBean(Object source, Class<V> clazz){
        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static <O, V>List<V> copyBeanList(List<O> list, Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("ss");

        HotArticleVo vo = copyBean(article, HotArticleVo.class);
        System.out.println(vo);
    }
}
