package com.ooad.scriptpro.service;


//import com.sun.deploy.net.HttpResponse;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    public final static String SESSION_KEY = "username";

    @Bean
    public SecurityInterceptor getSecurityInterceptor(){
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/userLogin");
        //addInterceptor.excludePathPatterns("/userHome");
        addInterceptor.excludePathPatterns("/signup");
        addInterceptor.excludePathPatterns("/userSignup");
        // api test
        addInterceptor.excludePathPatterns("/generateData");
        addInterceptor.excludePathPatterns("/findusers");
        addInterceptor.excludePathPatterns("/topPopular");
        addInterceptor.excludePathPatterns("/topLatest");
        addInterceptor.excludePathPatterns("/upload");
        addInterceptor.excludePathPatterns("/uploadscript");
        addInterceptor.excludePathPatterns("/run");
        addInterceptor.excludePathPatterns("/getScript");
        addInterceptor.excludePathPatterns("/vagueSearch");
        addInterceptor.excludePathPatterns("/getContent");
        // Welcome page
        addInterceptor.excludePathPatterns("/");
        addInterceptor.excludePathPatterns("/welcome");

        // Static files
        addInterceptor.excludePathPatterns("/css/**");
        addInterceptor.excludePathPatterns("/js/**");
        addInterceptor.excludePathPatterns("/img/**");
        addInterceptor.excludePathPatterns("/demo/**");
        addInterceptor.excludePathPatterns("/static/**");

        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter{
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            HttpSession httpSession = request.getSession();
            //return true;

            if(httpSession.getAttribute(SESSION_KEY) != null){
                return true;
            }
            System.out.println("session not found, redirect to log in");
            String url = "/login";
            response.sendRedirect(url);
            return false;


        }
    }

}
