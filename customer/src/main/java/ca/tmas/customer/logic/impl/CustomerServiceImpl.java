package ca.tmas.customer.logic.impl;

import ca.tmas.customer.dao.CustomerRepository;
import ca.tmas.customer.dto.CustomerDto;
import ca.tmas.customer.entities.Customer;
import ca.tmas.customer.logic.CustomerService;
import ca.tmas.customer.utility.CustomerStatus;
import ca.tmas.customer.utility.Gender;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final static long CSTCUS;

    private final static String REFCUS;

    private final ModelMapper mapper;

    private final CustomerRepository customerRepository;

    static {
        CSTCUS = 11111L;
        REFCUS = "cus-";
    }

    @Transactional
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer cus;
        String ref;
        if(customerRepository.findAllRefCustomer().isEmpty()) {
            ref = REFCUS + Long.toHexString(Long.MAX_VALUE % CSTCUS);
        } else {
            String maxRef = customerRepository.findAllRefCustomer().stream().map(x -> x.substring(4)).collect(Collectors.toSet()).stream().max(String::compareTo).get();
            long nextValueRef = Long.valueOf(maxRef, 16) + 1;
            ref = REFCUS + Long.toHexString(nextValueRef);
        }
        try {
            cus = mapper.map(customerDto, Customer.class);
            cus.setRefCustomer(ref.toUpperCase());
            cus.setCustomerStatus(CustomerStatus.AVAILABLE);
            return mapper.map(customerRepository.save(cus), CustomerDto.class);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Bad argument");
        }
    }

    @Override
    public Optional<CustomerDto> getCustomerByRef(String ref) {
        return Optional.ofNullable(mapper.map(customerRepository.findCustomerByRef(ref).get(), CustomerDto.class));
    }

    @Override
    public Set<String> getAllRefCustomer() {
        return (customerRepository.findAllRefCustomer().isEmpty())? Collections.emptySet(): customerRepository.findAllRefCustomer();
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        if(customerRepository.findAll().isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CustomerDto> customerDtoList = new ArrayList<>();
            customerRepository.findAll().forEach(cus -> customerDtoList.add(mapper.map(cus, CustomerDto.class)));
            return customerDtoList;
        }
    }

    @Override
    public List<CustomerDto> getAllCustomerByGender(Gender gender) {
        if(customerRepository.findAllCustomerByGender(gender).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CustomerDto> customerDtoList = new ArrayList<>();
            customerRepository.findAllCustomerByGender(gender).forEach(cus -> customerDtoList.add(mapper.map(cus, CustomerDto.class)));
            return customerDtoList;
        }
    }

    @Override
    public List<CustomerDto> getCustomerByAge(int age) {
        if(customerRepository.findCustomerByAge(age).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CustomerDto> customerDtoList = new ArrayList<>();
            customerRepository.findCustomerByAge(age).forEach(cus -> customerDtoList.add(mapper.map(cus, CustomerDto.class)));
            return customerDtoList;
        }
    }

    @Override
    public List<CustomerDto> getAllCustomerByAges(int age1, int age2) {
        if(customerRepository.findAllCustomerByAges(age1, age2).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CustomerDto> customerDtoList = new ArrayList<>();
            customerRepository.findAllCustomerByAges(age1, age2).forEach(cus -> customerDtoList.add(mapper.map(cus, CustomerDto.class)));
            return customerDtoList;
        }
    }

    @Override
    public List<CustomerDto> getAllCustomerByStatus(CustomerStatus customerStatus) {
        if(customerRepository.findAllCustomerByStatus(customerStatus).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CustomerDto> customerDtoList = new ArrayList<>();
            customerRepository.findAllCustomerByStatus(customerStatus).forEach(cus -> customerDtoList.add(mapper.map(cus, CustomerDto.class)));
            return customerDtoList;
        }
    }

    @Transactional
    @Override
    public Optional<CustomerDto> updatePatiallyCustomer(String ref, CustomerDto customerDto) {
        if((customerDto != null) && (customerRepository.findCustomerByRef(ref).isPresent()) && (customerRepository.findCustomerByRef(ref).get().getCustomerStatus().equals(CustomerStatus.AVAILABLE))) {
            Customer currentCustomer = mapper.map(customerDto, Customer.class);
            Customer dbCustomer = customerRepository.findCustomerByRef(ref).get();
            dbCustomer.setRefCustomer(ref);
            mapper.getConfiguration().setSkipNullEnabled(true);
            mapper.map(currentCustomer, dbCustomer);
            Customer updateCustomer = customerRepository.save(dbCustomer);
            return Optional.ofNullable(mapper.map(updateCustomer, CustomerDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<CustomerDto> updateTotallyCustomer(String ref, CustomerDto customerDto) {
        if((customerDto != null) && (customerRepository.findCustomerByRef(ref).isPresent())) {
            Customer currentCustomer = mapper.map(customerDto, Customer.class);
            currentCustomer.setId(customerRepository.findCustomerByRef(ref).get().getId());
            currentCustomer.setRefCustomer(customerRepository.findCustomerByRef(ref).get().getRefCustomer());
            Customer updateCustomer = customerRepository.save(currentCustomer);
            return Optional.ofNullable(mapper.map(updateCustomer, CustomerDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<CustomerDto> deleteCustomer(String ref) {
        if((customerRepository.findCustomerByRef(ref).isPresent()) && (customerRepository.findCustomerByRef(ref).get().getCustomerStatus().equals(CustomerStatus.AVAILABLE))) {
            Customer cus = customerRepository.findCustomerByRef(ref).get();
            cus.setCustomerStatus(CustomerStatus.UNAVAILABLE);
            return Optional.of(mapper.map(cus, CustomerDto.class));
        } else {
            return Optional.empty();
        }
    }
}
