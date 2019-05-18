package com.wqb.website.domains.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
@TableName("article_content")
public class ArticleContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    /**
     * 文章内容
     */
    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleContent{" +
        "articleId=" + articleId +
        ", content=" + content +
        "}";
    }
}
