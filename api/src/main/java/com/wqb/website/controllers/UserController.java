package com.wqb.website.controllers;

import com.wqb.website.commons.vo.Response;
import com.wqb.website.supports.util.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Shoven
 * @since 2019-05-17 18:01
 */

@RequestMapping("/user")
@RestController
public class UserController {

    @GetMapping("info")
    public ResponseEntity getUserInfo() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("roles", new String[]{"admin"});
        result.put("introduction","I am a super administrator");
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("name", "超级管理员");

        return ResponseUtils.ok(result);
    }
}
