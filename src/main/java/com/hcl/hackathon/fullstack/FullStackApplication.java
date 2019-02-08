package com.hcl.hackathon.fullstack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.hcl.hackathon.fullstack.model.Account;
import com.hcl.hackathon.fullstack.repository.AccountRepository;

@SpringBootApplication
public class FullStackApplication extends SpringBootServletInitializer {

	private final static Logger LOGGER = LoggerFactory.getLogger(FullStackApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FullStackApplication.class);
	}

	public static void main(String[] args) {
		LOGGER.info("App starting..");
		FullStackApplication app = new FullStackApplication();
		app.configure(new SpringApplicationBuilder(SpringApplicationBuilder.class)).run(args);
	}

	@Bean
	CommandLineRunner init(final AccountRepository accountRepository) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				accountRepository.save(new Account("testUser1", "password"));
				accountRepository.save(new Account("testUser2", "password"));
				accountRepository.save(new Account("testUser3", "password"));

			}

		};

	}

}
