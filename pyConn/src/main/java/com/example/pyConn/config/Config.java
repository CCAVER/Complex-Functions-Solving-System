package com.example.pyConn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/**").permitAll()  //允许/路径下的url
                //.antMatchers("/logPage","/reg","/","/doreg","/docheck","/pyReq/genId","/show").permitAll()
                .anyRequest().authenticated()    //操作必须是已登录状态
                .and()
                .formLogin()
                .loginPage("/logPage.html")//跳转自己定制的登录界面
                .loginProcessingUrl("/docheck")// 发送登录请求的路径
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((req, resp, authentication) -> {

                            Object principal = authentication.getPrincipal();
                            resp.setContentType("application/json;charset=utf-8");
                            //PrintWriter out = resp.getWriter();
                            //out.write(new ObjectMapper().writeValueAsString(principal));
                            //out.flush();
                            //out.close();
                    System.out.println(principal);
                        })
                .successForwardUrl("/dochecks")
                .failureForwardUrl("/logPage")
                .and()
                .csrf().disable()  //跨站请求伪造防护功能，禁用
                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String accountSql="select username,password,valid from user where username=?";
        String auSql="select DISTINCT c.username,a.authority from user c,ua a,aa ac where ac.id=ac.accid and a.id=ac.aid and c.username=?";
        //System.out.println("doing   config");
        auth.jdbcAuthentication().passwordEncoder(encoder).dataSource(dataSource).usersByUsernameQuery(accountSql).authoritiesByUsernameQuery(auSql);
    }
}
