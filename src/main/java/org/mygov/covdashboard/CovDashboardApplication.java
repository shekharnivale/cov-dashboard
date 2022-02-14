package org.mygov.covdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Profile("!IntegrationTestProfile")
@SpringBootApplication
@EnableScheduling
public class CovDashboardApplication {

	public static void main(String[] args) {

		SpringApplication.run(CovDashboardApplication.class, args);

	}

}
