/*
 * 
 */
package com.misyn.hrsystem.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Shanaka
 * Data base configuration 
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.misyn.hrsystem")
@PropertySource(value = {"classpath:system.properties"})
public class DatabaseConfiguration {

    private static final Logger LOGGER = Logger.getLogger(DatabaseConfiguration.class);

    @Autowired
    private Environment environment;

    /**
     * return Data source
     * @return 
     */
    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        DataSource dataSource = null;

        try {
            JndiTemplate jndiTemplate = new JndiTemplate();
            dataSource = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/mySql");

        } catch (NamingException ex) {
            LOGGER.error(ex);
        }
        return dataSource;
    }

    /**
     * return transaction manager
     * @return 
     */
    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(getDataSource());
        return dataSourceTransactionManager;
    }

}
