package com.wqb.website.controllers.article;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.wqb.website.controllers.base.BaseController;
import com.wqb.website.domains.article.ArticleType;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxb
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/articleType")
public class ArticleTypeController extends BaseController<ArticleType> {

}
