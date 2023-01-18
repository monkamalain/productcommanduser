package ca.tmas.customer.entities;

import ca.tmas.customer.utility.CustomerStatus;
import ca.tmas.customer.utility.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Customer extends EntityWithUUID implements Serializable {

    static final long serialVersionUID = 1L;

    @Column(name = "refCustomer", nullable = false, unique = true)
    String refCustomer;

    @Column(name = "firstName", nullable = false)
    String firstName;

    @Column(name = "lastName", nullable = false)
    String lastName;

    @Column(name = "gender", nullable = false, length = 8)
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "age", nullable = false)
    int age;

    @Column(name = "customerStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    CustomerStatus customerStatus;

}
