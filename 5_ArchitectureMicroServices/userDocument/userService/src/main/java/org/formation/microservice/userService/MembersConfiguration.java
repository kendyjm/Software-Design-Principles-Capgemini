package org.formation.microservice.userService;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * The accounts Spring configuration.
 * 
 * @author Paul Chapman
 */
@Configuration
@ComponentScan
@EntityScan("org.formation.microservice.userService")
@EnableJpaRepositories("org.formation.microservice.userService")
@PropertySource("classpath:db-config.properties")
public class MembersConfiguration {

	protected Logger logger;

	public MembersConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory "rewards" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/data.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> accounts = jdbcTemplate.queryForList("SELECT EMAIL FROM MEMBER");
		logger.info("System has " + accounts.size() + " accounts");

		// Populate with random registration date
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		for (Map<String, Object> item : accounts) {
			String email = (String) item.get("EMAIL");
			cal.set(Calendar.DAY_OF_MONTH, (int)System.currentTimeMillis()%28);
			jdbcTemplate.update("UPDATE MEMBER SET REGISTERED_DATE = ? WHERE email = ?", cal.getTime(), email);
		}

		return dataSource;
	}
}
