package com.wqb.website.services.impl;

import com.wqb.website.domains.User;
import com.wqb.website.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lombok.Lombok.checkNotNull;

/**
 * @author Shoven
 * @since 2019-05-17 16:20
 */

@Service
public class UserServiceImpl implements UserService {

    private static List<User> users = new ArrayList<>();

    static {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("123456");

        User editor = new User();
        editor.setUsername("editor");
        editor.setPassword("123456");

        users.add(admin);
        users.add(editor);
    }

    @Override
    public User getCurrentUser() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = principal instanceof UserDetails
//                ? ((UserDetails)principal).getUsername()
//                : (String) principal;

        return getOneByUserName("admin");
    }

    @Override
    public User getOneByUserName(String username) {
        checkNotNull(username, "用户名不能为空");
        return users.stream()
        .filter(user -> Objects.equals(user.getUsername(), username))
        .findFirst().orElse(null);
    }
}
