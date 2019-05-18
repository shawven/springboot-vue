package com.wqb.website.controllers.article;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wqb.website.commons.vo.ArticleParam;
import com.wqb.website.commons.vo.ArticleVO;
import com.wqb.website.services.article.ArticleService;
import com.wqb.website.supports.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wqb.website.controllers.base.BaseController;
import com.wqb.website.domains.article.Article;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<Article> {

    @Autowired
    ArticleService articleService;

    @Override
    @GetMapping("{id}")
    public ResponseEntity selectOne(@PathVariable Serializable id) {
        ArticleVO article = articleService.getArticle(id);
        if (article == null) {
            return ResponseUtils.notFound("暂无数据！");
        }
        return ResponseUtils.ok("获取文章成功。",article);
    }

    @PostMapping("/insert")
    public ResponseEntity insert(@Valid ArticleVO entity) {
        if (!articleService.insertArticle(entity)) {
            return ResponseUtils.unprocesable("添加失败！", entity);
        }
        return ResponseUtils.created("添加成功！", entity);
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid ArticleVO entity) {
        if (!articleService.updateArticle(entity)) {
            return ResponseUtils.unprocesable("更新失败！", entity);
        }
        return ResponseUtils.created("更新成功！", entity);
    }

    @Override
    @DeleteMapping
    public ResponseEntity delete(@RequestParam(value = "id", required = false) Serializable id,
                                 @RequestParam(value = "ids", required = false) String ids) {
        if (!articleService.deleteArticle(id,ids)) {
            return ResponseUtils.unprocesable("删除失败！");
        }
        return ResponseUtils.noContent();
    }

    @PutMapping("/hits")
    public ResponseEntity hits(@Valid ArticleVO entity) {
        if (!articleService.hitsArticle(entity)) {
            return ResponseUtils.unprocesable("更新文章点击数失败！", entity);
        }
        return ResponseUtils.created("更新文章点击数成功！", entity);
    }

    @GetMapping("/list")
    public ResponseEntity getArticleList(@Valid ArticleParam artParam){
        IPage<ArticleVO> articleIPage = articleService.getPageList(artParam);
        return ResponseUtils.ok("获取文章列表成功。",articleIPage);
    }
}
