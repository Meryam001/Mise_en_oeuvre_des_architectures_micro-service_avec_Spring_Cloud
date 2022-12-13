package com.example.billingservice;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductItemRestClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerRestClient,
            ProductItemRestClient productItemRestClient
    ){
        return args -> {
            Customer custom=customerRestClient.getCustomerById(1L);
            Bill bill=billRepository.save(new Bill(null,new Date(),null, custom.getId(), null));
            PagedModel<Product> productPagedModel=productItemRestClient.pageProducts();
            productPagedModel.forEach(p->{
                ProductItem productItem=new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(1+new Random().nextInt(100));
                productItem.setBill(bill);
                productItem.setProductID(p.getId());
                ProductItem save = productItemRepository.save(productItem);
                System.out.println(p);
                System.out.println(productItem);
                System.out.println("saved product item:"+save);
            });



        };
    }

}
