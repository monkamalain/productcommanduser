package ca.tmas.customer.dao;

import ca.tmas.customer.entities.Customer;
import ca.tmas.customer.utility.CustomerStatus;
import ca.tmas.customer.utility.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("Select cus From Customer cus Where cus.refCustomer like :ref")
    Optional<Customer> findCustomerByRef(@Param("ref") String ref);

    @Query("Select cus.refCustomer from Customer cus where cus.refCustomer is not null")
    Set<String> findAllRefCustomer();

    @Query("Select cus From Customer cus Where cus.gender = :gender")
    List<Customer> findAllCustomerByGender(@Param("gender") Gender gender);

    @Query("Select cus From Customer cus Where cus.age = :age")
    List<Customer> findCustomerByAge(@Param("age") int age);

    @Query("Select cus From Customer cus Where cus.age >= :age1 and cus.age <= :age2")
    List<Customer> findAllCustomerByAges(@Param("age1") int age1, @Param("age2") int age2);

    @Query("Select cus From Customer cus Where cus.customerStatus = :customerStatus")
    List<Customer> findAllCustomerByStatus(@Param("customerStatus") CustomerStatus customerStatus);

}
