package ca.tmas.customer.dto;

import ca.tmas.customer.utility.CustomerStatus;
import ca.tmas.customer.utility.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CustomerDto {

    String refCustomer;

    @NotEmpty(message = "Firstname of Customer may not be empty")
    String firstName;

    @NotEmpty(message = "Lastname of Customer may not be empty")
    String lastName;

    @NotEmpty(message = "Gender of Customer may not be empty")
    Gender gender;

    @NotEmpty(message = "Age of Customer may not be empty")
    int age;

    CustomerStatus customerStatus;

}
