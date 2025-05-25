package ir.entity;

import ir.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
}
