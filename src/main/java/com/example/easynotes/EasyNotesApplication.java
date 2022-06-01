package com.example.easynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
@Configuration : Any class annotated with @Configuration annotation is bootstrapped by Spring
and is also considered as a source of other bean definitions.

@EnableAutoConfiguration : This annotation tells Spring to automatically configure your application
based on the dependencies that you have added in the pom.xml file.
For example, If spring-data-jpa or spring-jdbc is in the classpath,
then it automatically tries to configure a DataSource by reading the database properties from application.properties file.

@ComponentScan : It tells Spring to scan and bootstrap other components
defined in the current package (com.example.easynotes) and all the sub-packages.
 */

@SpringBootApplication
//@EnableJpaAuditing
public class EasyNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyNotesApplication.class, args);
    }

}
