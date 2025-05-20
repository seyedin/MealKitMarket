package ir.entity;

//TODO : need to check.

import ir.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
//@Entity
//@Table(name = "discount_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCode extends BaseEntity {
    @NotBlank(message = "Code is mandatory")
    @Column(unique = true)
    private String code;

    @NotNull
    @Positive(message = "Percentage must be positive")
    @Max(value = 100, message = "Percentage cannot exceed 100")
    private Double percentage;

    @NotNull
    private LocalDateTime expiryDate;

    @NotNull
    private Boolean isActive = true;

//    @OneToMany(mappedBy = "discountCode", fetch = FetchType.LAZY)
//    private List<Order> orders;

}
