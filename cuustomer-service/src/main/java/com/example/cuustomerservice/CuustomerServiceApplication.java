package com.example.cuustomerservice;

import com.example.cuustomerservice.dao.CustomerRepository;
import com.example.cuustomerservice.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CuustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration) {

        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Customer.class);

                customerRepository.save(new Customer(null, "Enset", "contact@enset-media.ma"));
            customerRepository.save(new Customer(null, "FSTM", "contact@fstm.ma"));
            customerRepository.save(new Customer(null, "ENSAM", "contact@ensam.ma"));
            customerRepository.findAll().forEach(System.out::println);

        };
    }
}
