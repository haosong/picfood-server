package com.picfood.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by shawn on 2018/3/16.
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

}
