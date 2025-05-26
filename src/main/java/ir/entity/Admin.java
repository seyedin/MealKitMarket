package ir.entity;

import ir.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Admin extends BaseEntity {

    @OneToOne
    private User user;
}
