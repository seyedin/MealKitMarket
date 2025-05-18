package ir.entity;

import ir.entity.base.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class Role extends BaseEntity {

    private String roleName;
}
