package cn.com.taiji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2//开启Swagger
public class Swagger2Configuration {
	
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInf())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.com.taiji.controller"))
				.paths(PathSelectors.any()).build();
		
	}

	private ApiInfo buildApiInf() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("swagger2接口可视化")
				.description("UserManager接口文档")
				.termsOfServiceUrl("https://www.mi.com")
				.contact(new Contact("liuxin", "https://www.baidu.com", "xin549032340@gmail.com"))
				.build();
	}
	
}
