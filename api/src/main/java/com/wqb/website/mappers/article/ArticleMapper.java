package com.wqb.website.mappers.article;

import com.wqb.website.commons.vo.ArticleParam;
import com.wqb.website.commons.vo.ArticleVO;
import com.wqb.website.domains.article.Article;
import com.wqb.website.mappers.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
public interface ArticleMapper extends BaseMapper<Article> {

    ArticleVO selectArticle(Serializable id);

    List<ArticleVO> selectArticles();

    List<ArticleVO> selectPageList(@Param("artParam") ArticleParam artParam);

    long selectTotal(@Param("artParam") ArticleParam artParam);
}
