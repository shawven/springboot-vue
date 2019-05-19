package com.wqb.website.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shoven
 * @since 2019-05-18 23:32
 */
@RestController
public class IndexController {

    @PostMapping("/login/form")
    public ResponseEntity login() {
        return ResponseEntity.ok().build();
    }

}
