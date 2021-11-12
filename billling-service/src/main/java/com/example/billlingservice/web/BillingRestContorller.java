package com.example.billlingservice.web;

import com.example.billlingservice.dao.Billrepository;
import com.example.billlingservice.dao.ProductItemRepository;
import com.example.billlingservice.entities.Bill;
import com.example.billlingservice.feign.CustomerRestClient;
import com.example.billlingservice.feign.ProductItemRestClient;
import com.example.billlingservice.model.Customer;
import com.example.billlingservice.model.Product;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestContorller {

    private Billrepository billrepository;
    private ProductItemRepository productItemRepository;

    private ProductItemRestClient productItemRestClient;
    @Autowired
    private CustomerRestClient customerRestClient;


    public BillingRestContorller(Billrepository billrepository, ProductItemRepository productItemRepository, ProductItemRestClient productItemRestClient, CustomerRestClient customerRestClient) {
        this.billrepository = billrepository;
        this.productItemRepository = productItemRepository;
        this.productItemRestClient = productItemRestClient;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill = billrepository.findById(id).get();

        Customer customer = customerRestClient.getCustomerByid(bill.getCutomerId());
        bill.setCustomer(customer);
        PagedModel<Product> pagedModel = productItemRestClient.pageProducts(0,5);
        bill.getProductItems().forEach(pi->{
            Product product = productItemRestClient.getProductBYid(pi.getProductId());
            pi.setProduct(product);
        });
        return bill;
    }
}
