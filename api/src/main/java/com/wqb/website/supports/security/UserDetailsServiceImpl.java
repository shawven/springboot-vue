
package com.wqb.website.supports.security;

import com.wqb.security.core.validate.code.MobileUserDetailsService;
import com.wqb.website.domains.User;
import com.wqb.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Service;


/**
 * security oauth2和社交登陆用户服务
 *
 * @author Shoven
 * @since 2019-04-22 17:35
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, MobileUserDetailsService {

	@Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户名
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOneByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        return buildUserDetails(user);
    }



	private UserDetails buildUserDetails(User user) {
        // 0 新建 1启用 2禁用
        return new SocialUser(user.getUsername(), passwordEncoder.encode(user.getPassword()),
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));

	}

    @Override
    public UserDetails loadUserByMobileNumber(String mobile) throws UsernameNotFoundException {
        return loadUserByUsername(mobile);
    }
}
