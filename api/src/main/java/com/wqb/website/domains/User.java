package com.wqb.website.domains;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Shoven
 * @since 2019-05-17 16:18
 */
@Data
public class User implements Serializable {

    private String username;

    private String password;
}
