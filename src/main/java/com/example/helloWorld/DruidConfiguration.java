package com.example.helloWorld;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletRegistration;

@Configuration

public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        ServletRegistrationBean slBean = new ServletRegistrationBean(
                new StatViewServlet(),"/druid/*");

        // 白名单
        slBean.addInitParameter("allow","127.0.0.1");

        // 黑名单
        slBean.addInitParameter("deny","192.168.1.1");

        // user
        slBean.addInitParameter("loginUsername","admin");
        // passwd
        slBean.addInitParameter("loginPassword","123456");

        // 是否可以重置数据
        slBean.addInitParameter("resetEnable","false");

        return  slBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean frBean = new FilterRegistrationBean(
            new WebStatFilter());

        frBean.addUrlPatterns("/*");

        frBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.css,*.ico,/druid/*");

        return frBean;

    }
}
