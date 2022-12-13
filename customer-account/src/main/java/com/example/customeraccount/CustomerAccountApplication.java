package com.example.customeraccount;

import com.example.customeraccount.entities.Customer;
import com.example.customeraccount.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerAccountApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(CustomerAccountApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Customer.class);
        return args ->{
        customerRepository.save(new Customer(null,"Mohammed","med@gmail.com"));
        customerRepository.save(new Customer(null,"Yassine","Yassine@gmail.com"));
        customerRepository.save(new Customer(null,"Karim","karim@gmail.com"));
        customerRepository.findAll().forEach(c->{
            System.out.println(c.toString());
        });

        };
    }

}
