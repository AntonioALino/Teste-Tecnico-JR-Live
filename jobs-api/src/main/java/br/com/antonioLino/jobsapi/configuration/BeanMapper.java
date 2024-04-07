package br.com.antonioLino.jobsapi.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanMapper {
    

    public static Object mapper;

    @Bean
    public static ModelMapper mapper(){
        return new ModelMapper();
    }
 
}
