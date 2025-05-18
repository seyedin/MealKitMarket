package ir.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer extends User {
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;
}
