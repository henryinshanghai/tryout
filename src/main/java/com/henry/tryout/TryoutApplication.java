package com.henry.tryout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@RestController
@EnableSwagger2 // add one more api: /v2/api-docs
public class TryoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryoutApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@Bean
	public Docket swaggerConfiguration() { // Docket用于定制化 生成的html页面中的信息

		// return a prepared Docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select() // get the builder object
				.paths(PathSelectors.ant("/api/*")) // add restriction01: git rid of unnecessary endpoint(some out-of-box spring boot endpoint)
				.apis(RequestHandlerSelectors.basePackage("com.henry.tryout.swagger")) // add restriction02: search controller only in given package
				.build()
				.apiInfo(apiDetails()); // after build(), use apiInfo() to add the application metadata

	}


	// add in application metadata
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Address book API",
				"Sample API for JavaBrains tutorial",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Double K", "http://javabrains.io", "a@b.com"), // this is different Contact class than our own
				"API License",
				"http://javabrains.io",
				Collections.emptyList()
		);
	}

}
