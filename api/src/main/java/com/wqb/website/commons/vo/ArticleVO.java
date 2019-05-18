package com.wqb.website.commons.vo;

import com.wqb.website.domains.article.Article;
import com.wqb.website.domains.article.ArticleContent;

/**
 * <p>
 *
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
public class ArticleVO extends Article {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    private Integer typeId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 栏目名称
     */
    private String artTypeName;

    /**
     * 所属平台：0财税官网，1云服务软件，2商城
     */
    private Integer platform;

    /**
     * 栏目状态：0禁用，1启用
     */
    private Integer artTypeStatus;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArtTypeName() {
        return artTypeName;
    }

    public void setArtTypeName(String artTypeName) {
        this.artTypeName = artTypeName;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getArtTypeStatus() {
        return artTypeStatus;
    }

    public void setArtTypeStatus(Integer artTypeStatus) {
        this.artTypeStatus = artTypeStatus;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "typeId=" + typeId +
                ", content='" + content + '\'' +
                ", artTypeName='" + artTypeName + '\'' +
                ", platform=" + platform +
                ", artTypeStatus=" + artTypeStatus +
                '}';
    }

    public Article toArticle(){
        Article article = new Article();
        article.setId(article.getId());
        article.setSubject(this.getSubject());
        article.setDescription(this.getDescription());
        article.setClickCount(this.getClickCount());
        article.setSortNo(this.getSortNo());
        article.setKeyword(this.getKeyword());
        article.setAuthor(this.getAuthor());
        article.setCoverPic(this.getCoverPic());
        article.setIsEnabled(this.getIsEnabled());
        article.setCreateTime(this.getCreateTime());
        article.setUpdateTime(this.getUpdateTime());
        return article;
    }

    public ArticleContent toArticleContent(){
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArticleId(this.getId());
        articleContent.setContent(this.getContent());
        return articleContent;
    }
}
