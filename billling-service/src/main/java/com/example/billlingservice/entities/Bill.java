package com.example.billlingservice.entities;

import com.example.billlingservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Date billingDate;
    @OneToMany(mappedBy = "bill")
    private Collection <ProductItem> productItems;
    private long cutomerId;
    @Transient
    private Customer customer;
}
