package com.wqb.website.services.impl.article;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.website.commons.vo.ArticleParam;
import com.wqb.website.commons.vo.ArticleVO;
import com.wqb.website.domains.article.Article;
import com.wqb.website.mappers.article.ArticleMapper;
import com.wqb.website.services.article.ArticleContentService;
import com.wqb.website.services.article.ArticleService;
import com.wqb.website.services.base.BaseServiceImpl;
import com.wqb.website.supports.exceptions.WqbException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleContentService articleContentService;

    @Override
    public ArticleVO getArticle(Serializable id) {
        return articleMapper.selectArticle(id);
    }

    @Override
    public List<ArticleVO> getArticles() {
        return articleMapper.selectArticles();
    }

    @Override
    public IPage<ArticleVO> getPageList(ArticleParam artParam) {
        List<ArticleVO> articleVOList = articleMapper.selectPageList(artParam);

        Page<ArticleVO> newPage = new Page<>(artParam.getCurrent(), artParam.getSize());
        newPage.setRecords(articleVOList);
        long total = articleMapper.selectTotal(artParam);
        newPage.setTotal(total);
        return newPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertArticle(ArticleVO entity) {
        if (!this.save(entity.toArticle())) {
            throw new WqbException("保存文章信息失败");
        }
        if (!articleContentService.save(entity.toArticleContent())) {
            throw new WqbException("保存文章内容失败");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticle(ArticleVO entity) {
        if (!this.updateById(entity.toArticle())) {
            throw new WqbException("更新文章信息失败");
        }
        if (!articleContentService.updateById(entity.toArticleContent())) {
            throw new WqbException("更新文章内容失败");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteArticle(Serializable id, String ids) {
        if (id != null) {
            if (!this.removeById(id)) {
                throw new WqbException("删除文章信息失败");
            }
            if (!articleContentService.removeById(id)) {
                throw new WqbException("删除文章内容失败");
            }
        }
        if (StringUtils.isNotEmpty(ids)) {
            String[] arr = StringUtils.split(ids, ",");
            if (!this.removeByIds(Arrays.asList(arr))) {
                throw new WqbException("批量删除文章信息失败");
            }
            if (!articleContentService.removeByIds(Arrays.asList(arr))) {
                throw new WqbException("批量删除文章内容失败");
            }
        }
        return true;
    }

    @Override
    public boolean hitsArticle(ArticleVO entity) {
        Article article = entity.toArticle();
        article.setClickCount(entity.getClickCount()+1);
        if (!this.updateById(article)) {
            throw new WqbException("更新文章点击数失败");
        }
        return true;
    }

}
