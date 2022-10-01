//package vougth.api.docs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("vougth.api.controller"))
//                .build()
//                .apiInfo(metaData());
//    }
//    private ApiInfo metaData(){
//        return new ApiInfoBuilder()
//                .title("Feed to Events generator by Vougth")
//                .description("Software to generate events based on like events of clients")
//                .version("1.0")
//                .contact(new Contact(
//                        "Eduardo Cardoso",
//                        "https://www.linkedin.com/in/eduardo-cardoso-b5745a1a3/",
//                        "eduardocardoso.kami@gmail.com"))
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//                .build();
//    }
//}
