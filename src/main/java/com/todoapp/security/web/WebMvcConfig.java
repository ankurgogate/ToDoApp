package com.todoapp.security.web;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.todoapp")
@EnableJpaRepositories(basePackages = {
        "com.todoapp"
})
public class WebMvcConfig extends WebMvcConfigurerAdapter{
 
 @Bean
 public ViewResolver viewResolver(){
  InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
  viewResolver.setPrefix("/WEB-INF/view/");
  viewResolver.setSuffix(".jsp");
  return viewResolver;
 }
 
 @Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/todo");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("Home@123");
		return driverManagerDataSource;
	}
 @Bean
 LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, 
                                                             Environment env) {
     LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
     entityManagerFactoryBean.setDataSource(dataSource);
     entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
     entityManagerFactoryBean.setPackagesToScan("com.todoapp");

     Properties jpaProperties = new Properties();
  
     //Configures the used database dialect. This allows Hibernate to create SQL
     //that is optimized for the used database.
     jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

     //Specifies the action that is invoked to the database when the Hibernate
     //SessionFactory is created or closed.
     jpaProperties.put("hibernate.hbm2ddl.auto", "validate");

     //Configures the naming strategy that is used when Hibernate creates
     //new database objects and schema elements
     jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

     //If the value of this property is true, Hibernate writes all SQL
     //statements to the console.
     jpaProperties.put("hibernate.show_sql","false");

     //If the value of this property is true, Hibernate will format the SQL
     //that is written to the console.
     jpaProperties.put("hibernate.format_sql","true");
     entityManagerFactoryBean.setJpaProperties(jpaProperties);

     return entityManagerFactoryBean;
 }
 @Bean
 JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
     JpaTransactionManager transactionManager = new JpaTransactionManager();
     transactionManager.setEntityManagerFactory(entityManagerFactory);
     return transactionManager;
 }
 
 @Bean
 public JdbcTemplate jdbcTemplate(DataSource dataSource) {
     JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
     jdbcTemplate.setResultsMapCaseInsensitive(true);
     return jdbcTemplate;
 }
 @Bean
 protected MappingJackson2HttpMessageConverter converter() {
 	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
     //do your customizations here...
     return converter;
 }
 @Override
 public void addCorsMappings(CorsRegistry registry) {
     registry.addMapping("/**");
 }
}