package org.ybs.customercommandsside;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CustomerCommandsSideApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerCommandsSideApplication.class, args);
    }
    @Bean
    public CommandBus commandBus(){
        return SimpleCommandBus.builder().build();
    }

}
