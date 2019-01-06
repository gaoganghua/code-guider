package com.code.guider;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class CodeGuiderApplication {

    /**
     * 引入自定义的yml文件
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        String[] paths = null;
        paths = new String[]{"generate/application-generate.yml", "tabletobean/application-datasource.yml", "parsefile/application-parse.yml"};
        ClassPathResource[] resources = new ClassPathResource[paths.length];

        for(int i=0;i<paths.length;i++){
            resources[i] = new ClassPathResource(paths[i]);
        }

        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(resources);
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
    public static void main(String[] args) {
        SpringApplication.run(CodeGuiderApplication.class, args);
    }

}

