package edu.vinaenter.configs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import edu.vinaenter.constants.GlobalsConstant;

@Configuration
public class JdbcConfig {
	
	@Bean("dataSource") // IOC
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(GlobalsConstant.DRIVER_NAME);
		dataSource.setUrl(GlobalsConstant.URL);
		dataSource.setUsername(GlobalsConstant.USERNAME);
		dataSource.setPassword(GlobalsConstant.PASSWORD);
		return dataSource;
	}
	
	@Bean("jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource()); //DI
		return jdbcTemplate;
	}
}
