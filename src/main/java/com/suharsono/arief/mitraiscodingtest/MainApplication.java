package com.suharsono.arief.mitraiscodingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MainApplication {
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MainApplication.class, args);
        
        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean(DispatcherServlet.class);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
    
}
