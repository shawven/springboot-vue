package com.wqb.website.services.article;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wqb.website.commons.vo.ArticleParam;
import com.wqb.website.commons.vo.ArticleVO;
import com.wqb.website.domains.article.Article;
import com.wqb.website.services.base.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
public interface ArticleService extends BaseService<Article> {

    /**
     * 获取文章
     * @param id
     * @return
     */
    ArticleVO getArticle(Serializable id);

    /**
     * 获取文章列表
     * @return
     */
    List<ArticleVO> getArticles();

    /**
     * 获取文章分页列表
     * @param artParam
     * @return
     */
    IPage<ArticleVO> getPageList(ArticleParam artParam);

    boolean insertArticle(ArticleVO entity);

    boolean updateArticle(ArticleVO entity);

    boolean deleteArticle(Serializable id, String ids);

    /**
     * 点击打开文章
     * @param entity
     * @return
     */
    boolean hitsArticle(ArticleVO entity);
}
