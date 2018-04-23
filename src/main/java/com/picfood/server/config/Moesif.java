package com.picfood.server.config;

import com.moesif.servlet.MoesifFilter;
import javax.servlet.Filter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.*;

/**
 * Created by shawn on 2018/4/23.
 */
@Configuration
public class Moesif extends WebMvcConfigurerAdapter {

    @Bean
    public Filter moesifFilter() {
        return new MoesifFilter("eyJhcHAiOiIyMzc6MzIiLCJ2ZXIiOiIyLjAiLCJvcmciOiI0MjA6MzMiLCJpYXQiOjE1MjQ0NDE2MDB9.9I0jIKgFCYVUvjbuDT-TG6kF2RVVkEdfDddqW2yNiMk");
    }
}