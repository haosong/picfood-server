package com.picfood.server.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

/**
 * Created by Shuqi on 18/3/20.
 */
public class mapperUtil {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
