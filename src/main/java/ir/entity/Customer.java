package ir.entity;

import ir.entity.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer extends BaseEntity {
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToOne
    private User user;
}
