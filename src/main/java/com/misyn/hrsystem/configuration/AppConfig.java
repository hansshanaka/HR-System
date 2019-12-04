/**
 *
 */
package com.misyn.hrsystem.configuration;

import com.misyn.hrsystem.util.SchedulBean;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Shanaka
 * Following are the configuration details
 */


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.misyn.hrsystem")
@EnableScheduling
public class AppConfig extends WebMvcConfigurerAdapter implements SchedulingConfigurer {

    private static final Logger LOGGER = Logger.getLogger(AppConfig.class);

    @Bean
    public SchedulBean bean() {
        return new SchedulBean();
    }

    /**
     * 
     * @param taskRegistrar 
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    /**
     * 
     * @return 
     */
    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }

    /**
     * allow read web-inf view
     * @return 
     */
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 
     * @return 
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }

    /**
     * 
     * @param registry 
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
    }

}
