package ca.tmas.product.entities;

import ca.tmas.product.utility.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
public class Product extends EntityWithUUID implements Serializable {

    static final long serialVersionUID = 1L;

    @Column(name = "refProduct", nullable = false, unique = true)
    String refProduct;

    @Column(name = "nameProduct", nullable = false)
    String nameProduct;

    @Column(name = "descriptionProduct", nullable = false)
    String descriptionProduct;

    @Column(name = "priceProduct", nullable = false)
    double priceProduct;

    @Enumerated(EnumType.STRING)
    @Column(name = "productStatus", nullable = false)
    ProductStatus productStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "saleDate", nullable = false)
    LocalDateTime saleDate;

}
