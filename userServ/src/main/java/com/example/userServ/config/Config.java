package com.example.userServ.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.example.userServ.service.MySecure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    MySecure mySecure;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                //.antMatchers("/**").permitAll()  //允许/路径下的url
                .antMatchers("/logPage","/reg","/","/doreg","/docheck").permitAll()
                .anyRequest().authenticated()    //操作必须是已登录状态
                .and()
                .formLogin()
                .loginPage("/logPage.html")//跳转自己定制的登录界面
                .loginProcessingUrl("/docheckA")// 发送登录请求的路径
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/py")
                .failureForwardUrl("/logPage0")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable()  //跨站请求伪造防护功能，禁用
                .httpBasic()
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String accountSql="select username,password,valid from user where username=?";
        String auSql="select DISTINCT c.username,a.authority from user c,ua a,aa ac where c.uid=ac.accid and a.id=ac.aid and c.username=?";
        System.out.println("doing   config"+auSql);
        auth.jdbcAuthentication().passwordEncoder(encoder).dataSource(dataSource).usersByUsernameQuery(accountSql).authoritiesByUsernameQuery(auSql);
    }
}
