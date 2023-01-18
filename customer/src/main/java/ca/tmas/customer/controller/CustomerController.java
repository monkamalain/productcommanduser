package ca.tmas.customer.controller;

import ca.tmas.customer.dto.CustomerDto;
import ca.tmas.customer.logic.CustomerService;
import ca.tmas.customer.utility.CustomerStatus;
import ca.tmas.customer.utility.Gender;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@RefreshScope
@EnableFeignClients(basePackages = { "ca.tmas.customer" })
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/createCustomer")
    synchronized public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            return new ResponseEntity<CustomerDto>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerByRef/{ref}")
    synchronized public ResponseEntity<Optional<CustomerDto>> getCustomerByRef(@PathVariable(value = "ref") String ref) {
        if(customerService.getCustomerByRef(ref).isPresent()) {
            return new ResponseEntity<Optional<CustomerDto>>(customerService.getCustomerByRef(ref), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CustomerDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllRefCustomer")
    synchronized public ResponseEntity<Set<String>> getAllRefCustomer() {
        if(customerService.getAllRefCustomer().isEmpty()) {
            return new ResponseEntity<Set<String>>(Collections.emptySet(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Set<String>>(customerService.getAllRefCustomer(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCustomer")
    synchronized public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        if(customerService.getAllCustomer().isEmpty()) {
            return new ResponseEntity<List<CustomerDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CustomerDto>>(customerService.getAllCustomer(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCustomerByGender/{gender}")
    synchronized public ResponseEntity<List<CustomerDto>> getAllCustomerByGender(@PathVariable(value = "gender") Gender gender) {
        if(customerService.getAllCustomerByGender(gender).isEmpty()) {
            return new ResponseEntity<List<CustomerDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CustomerDto>>(customerService.getAllCustomerByGender(gender), HttpStatus.OK);
        }
    }

    @GetMapping("/getCustomerByAge/{age}")
    synchronized public ResponseEntity<List<CustomerDto>> getCustomerByAge(@PathVariable(value = "age") int age) {
        if(customerService.getCustomerByAge(age).isEmpty()) {
            return new ResponseEntity<List<CustomerDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CustomerDto>>(customerService.getCustomerByAge(age), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCustomerByAges/{age1}/{age2}")
    synchronized public ResponseEntity<List<CustomerDto>> getAllCustomerByAges(@PathVariable(value = "age1") int age1, @PathVariable(value = "age2") int age2) {
        if(customerService.getAllCustomerByAges(age1, age2).isEmpty()) {
            return new ResponseEntity<List<CustomerDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CustomerDto>>(customerService.getAllCustomerByAges(age1, age2), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCustomerByStatus/{customerStatus}")
    synchronized public ResponseEntity<List<CustomerDto>> getAllCustomerByStatus(@PathVariable(value = "age1")CustomerStatus customerStatus) {
        if(customerService.getAllCustomerByStatus(customerStatus).isEmpty()) {
            return new ResponseEntity<List<CustomerDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CustomerDto>>(customerService.getAllCustomerByStatus(customerStatus), HttpStatus.OK);
        }
    }

    @PatchMapping("/updatePatiallyCustomer/{ref}")
    synchronized public ResponseEntity<Optional<CustomerDto>> updatePatiallyCustomer(@PathVariable(value = "ref") String ref, @RequestBody CustomerDto customerDto) {
        if(customerService.updatePatiallyCustomer(ref, customerDto).isPresent()) {
            return new ResponseEntity<Optional<CustomerDto>>(customerService.updatePatiallyCustomer(ref, customerDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CustomerDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTotallyCustomer/{ref}")
    synchronized public ResponseEntity<Optional<CustomerDto>> updateTotallyCustomer(@PathVariable(value = "ref") String ref, @RequestBody CustomerDto customerDto) {
        if(customerService.updateTotallyCustomer(ref, customerDto).isPresent()) {
            return new ResponseEntity<Optional<CustomerDto>>(customerService.updateTotallyCustomer(ref, customerDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CustomerDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCustomer/{ref}")
    synchronized public ResponseEntity<Optional<CustomerDto>> deleteCustomer(@PathVariable(value = "ref") String ref) {
        if(customerService.deleteCustomer(ref).isPresent()) {
            return new ResponseEntity<Optional<CustomerDto>>(customerService.deleteCustomer(ref), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CustomerDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

}
