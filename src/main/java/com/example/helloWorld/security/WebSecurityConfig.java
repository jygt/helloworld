package com.example.helloWorld.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

@Configuration
// 开启Security安全框架
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 应用自定义的CustomUserService类
    @Bean
    public CustomUserService customDbUserService(){
        return new CustomUserService();
    }

    // 路由策略和访问权限的简单配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .formLogin()    // 启动默认登录页面
                .failureUrl("/login?error") // 登录失败返回页面
                .defaultSuccessUrl("/url/test") // 登录成功跳转页面
                .permitAll();   // 登录页面全部权限可访问

        super.configure(http);
        //*/

        http.authorizeRequests()
                .antMatchers("/","index","/login","/login-error","/401","/css/**","/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage( "/login" ).failureUrl( "/login-error" )
                .and()
                .exceptionHandling().accessDeniedPage( "/401" );
        http.logout().logoutSuccessUrl( "/" );

    }

    // 设置内存用户，并配置权限
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(customDbUserService())
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        System.out.println(charSequence.toString());
                        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                        boolean res = s.equals( encode );
                        return res;
                    }
                })
                ;
        /*
                .inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("gaoy").password("abcd1234").roles("ADMIN")
                .and()
                .withUser("client").password("abcd1234").roles("USER");
         //*/

    }
}
