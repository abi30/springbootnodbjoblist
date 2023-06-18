package com.rakibgrup.joblisting;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;


// Other necessary imports

@SpringBootApplication

@OpenAPIDefinition(
		info = @Info(
				title = "spring with mongodb jobListing",
				version ="1.0.1",
				description = "This project is only for practice",
				termsOfService = "runCodeNew",
				contact= @Contact(
						name="Abdulla al rakib",
						email="rakib0751@gmail.com"
				 ),
				license = @License(
						name = "License",
						url = "https://infoscreen.live"
				)
		    )
     )

public class JoblistingApplication {

	public static void main(String[] args) {

		SpringApplication.run(JoblistingApplication.class, args);
	}
}
