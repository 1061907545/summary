package com.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.service.UserService;

@Configuration
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

	@Bean  
    public AuthenticationProvider authenticationProvider(){  
        AuthenticationProvider authenticationProvider=new AuthenticationProviderCustom();  
        return authenticationProvider;  
    }  
	
	@Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
        //自定义AuthenticationProvider  
        auth.authenticationProvider(authenticationProvider());  
    }  
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/pic/**","/css/**","/js/**","/lib/**");//放掉
	}
	
	//使用官方认证
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
          .authorizeRequests()  
          .antMatchers("/login").permitAll()//访问：/home 无需登录认证权限  
          .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问  
          //.antMatchers("/index").hasAuthority("ADMIN") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示  
          .and()
          .csrf()
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
          .and()  
          .formLogin()  
          .loginPage("/login")//指定登录页是”/login”  
          .permitAll()
          .defaultSuccessUrl("/index")//登录成功后默认跳转到"/index"
          .failureUrl("/login?error=true")//登录失败跳转 在前端
          //.successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。  
          .and()  
          .logout() 
          .logoutUrl("/logout")
          .logoutSuccessUrl("/login") //退出登录后的默认网址是”/home”  
          .permitAll()  
          .invalidateHttpSession(true); 
          //.and()  
          //.rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表  
          //.tokenValiditySeconds(1209600);  
    	  http.httpBasic().disable().headers().disable().csrf().disable();//关闭csrf
    }
    @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
