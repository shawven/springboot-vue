//
//package com.wqb.website.supports.security;
//
//import com.wqb.security.core.authentication.configurer.AuthorizationConfigProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.stereotype.Component;
//
//
///**
// * @author Shoven
// * @since 2019-04-22 17:36
// */
//@Component
//public class WqbConfigProvider implements AuthorizationConfigProvider {
//
//	@Override
//	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
//        config.antMatchers(
//                "/partner/contact",
//                "/trialCustomer/status"
//        ).permitAll();
//		return true;
//	}
//
//}
