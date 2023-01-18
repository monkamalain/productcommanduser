package ca.tmas.customer.logic;

import ca.tmas.customer.dto.CustomerDto;
import ca.tmas.customer.utility.CustomerStatus;
import ca.tmas.customer.utility.Gender;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    Optional<CustomerDto> getCustomerByRef(String ref);

    Set<String> getAllRefCustomer();

    List<CustomerDto> getAllCustomer();

    List<CustomerDto> getAllCustomerByGender(Gender gender);

    List<CustomerDto> getCustomerByAge(int age);

    List<CustomerDto> getAllCustomerByAges(int age1, int age2);

    List<CustomerDto> getAllCustomerByStatus(CustomerStatus customerStatus);

    Optional<CustomerDto> updatePatiallyCustomer(String ref, CustomerDto customerDto);

    Optional<CustomerDto> updateTotallyCustomer(String ref, CustomerDto customerDto);

    Optional<CustomerDto> deleteCustomer(String ref);

}
