package com.example.helloWorld.Filter;


// import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "urlFilter",urlPatterns = "/url/*")

public class TUrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        System.out.println("---------->>> init ");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException,ServletException{
        System.out.println("---------->>> doFilter ");
        filterChain.doFilter(req,res);
    }
    @Override
    public void destroy(){
        System.out.println("---------->>> destroy ");
    }
}
