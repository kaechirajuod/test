package com.hcl.hackathon.fullstack.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories(basePackages = "com.hcl.hackathon.fullstack.repository",
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JpaConfiguration {
 
	@Value("${datasourcefullstackapp.jpa.hibernate.format_sql}")
	private String  formatSql;
	
	@Value("${datasourcefullstackapp.jpa.hibernate.hbm2ddl.method}")
	private String  hbm2ddlMethod;
	
	@Value("${datasourcefullstackapp.jpa.database-platform}")
	private String dialect;
	
	@Value("${datasourcefullstackapp.jpa.hibernate.show_sql}")
	private String  showSql;
	
	@Value("${datasourcefullstackapp.defaultSchema}")
	private String  defaultSchema;
	
	@Value("${datasourcefullstackapp.maxIdle:10}")
	private int maxIdle;
	
	@Value("${datasourcefullstackapp.minIdle:5}")
	private int minIdle;

	/*
	 * Populate SpringBoot DataSourceProperties object directly from application.yml 
	 * based on prefix.
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasourcefullstackapp")
	public DataSourceProperties dataSourceProperties(){
		System.out.println("dialect------------"+dialect);

		return new DataSourceProperties();
	}

	/*
	 * Configure dbcp2 pooled DataSource.
	 */
	@Bean
	public DataSource dataSource() {
		DataSourceProperties dataSourceProperties = dataSourceProperties();

        BasicDataSource dataSource = (BasicDataSource) DataSourceBuilder
					.create(dataSourceProperties.getClassLoader())
					.driverClassName(dataSourceProperties.getDriverClassName())
					.url(dataSourceProperties.getUrl())
					.username(dataSourceProperties.getUsername())
					.password(dataSourceProperties.getPassword())
					.type(BasicDataSource.class)
					.build();
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
 			return dataSource;
	}

	/*
	 * Entity Manager Factory setup.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(new String[] { "com.hcl.hackathon.fullstack.model" });
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}

	/*
	 * Provider specific adapter.
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}

	/*
	 * Here you can specify any provider specific properties.
	 */
	private Properties jpaProperties() {
		System.out.println("dialect------------"+dialect);
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.hbm2ddl.auto", hbm2ddlMethod);
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.format_sql", formatSql);
		if(StringUtils.isNotBlank(defaultSchema)){
			properties.put("hibernate.default_schema", defaultSchema);
		}
		return properties;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}

}
