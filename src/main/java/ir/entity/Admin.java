package ir.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("ADMIN")
public class Admin extends User {

}
