package ca.tmas.command.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    String addressLine1;

    String addressLine2;

    String addressLine3;

    String cityAddress;

    String provinceAddress;

    String countryAddress;

}
