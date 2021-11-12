package com.example.billlingservice;

import com.example.billlingservice.dao.Billrepository;
import com.example.billlingservice.dao.ProductItemRepository;
import com.example.billlingservice.entities.Bill;
import com.example.billlingservice.entities.ProductItem;
import com.example.billlingservice.feign.CustomerRestClient;
import com.example.billlingservice.feign.ProductItemRestClient;
import com.example.billlingservice.model.Customer;
import com.example.billlingservice.model.Product;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BilllingServiceApplication implements CommandLineRunner {

    @Autowired
    CustomerRestClient customerRestClient;
    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;
    @Autowired
    ProductItemRestClient productItemRestClient;
    @Autowired
    Billrepository billrepository;
    @Autowired
    ProductItemRepository productItemRepository;
    public static void main(String[] args) {
        SpringApplication.run(BilllingServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Customer customer = customerRestClient.getCustomerByid(1L);
        Bill bill = billrepository.save(new Bill(null,new Date(),null,customer.getId(),null));
        PagedModel<Product> productPagedModel = productItemRestClient.pageProducts(0,5);
        productPagedModel.forEach(p->{
            ProductItem productItem = new ProductItem();
            productItem.setPrice(p.getPrice());
            productItem.setQuantite(1+new Random().nextInt(100));
            productItem.setBill(bill);
            productItem.setProductId(p.getId());
            productItemRepository.save(productItem);
        });
        System.out.println("Test ******");
        System.out.println(customer.getId());
        System.out.println(customer.getName());
        System.out.println(customer.getEmail());
    }
}
