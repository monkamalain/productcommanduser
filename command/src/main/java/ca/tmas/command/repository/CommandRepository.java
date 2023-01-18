package ca.tmas.command.repository;

import ca.tmas.command.model.Command;
import ca.tmas.command.utility.StateCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CommandRepository extends JpaRepository<Command, String> {

    @Query("Select cmd From Command cmd Where cmd.refCommand like :ref")
    Optional<Command> findCommandByRef(@Param("ref") String ref);

    @Query("Select cmd.refCommand From Command cmd Where cmd.refCommand is not null")
    Set<String> findAllRefCommand();

    @Query("Select cmd From Command cmd Where cmd.nameCommand like :nameCommand")
    Optional<Command> findCommandByNameCommand(@Param("nameCommand") String nameCommand);

    @Query("Select cmd From Command cmd Where cmd.address.addressLine1 like :houseNumber")
    Optional<Command> findCommandByHouseNumber(@Param("houseNumber") String houseNumber);

    @Query("Select cmd From Command cmd Where cmd.address.addressLine2 like :street")
    Optional<Command> findCommandByStreet(@Param("street") String street);

    @Query("Select cmd From Command cmd Where cmd.address.addressLine3 like :zipCode")
    Optional<Command> findCommandByZipCode(@Param("zipCode") String zipCode);

    @Query("Select cmd From Command cmd Where cmd.address.cityAddress like :city")
    List<Command> findCommandByCity(@Param("city") String city);

    @Query("Select cmd From Command cmd Where cmd.address.provinceAddress like :province")
    List<Command> findCommandByProvince(@Param("province") String province);

    @Query("Select cmd From Command cmd Where cmd.address.countryAddress like :country")
    List<Command> findCommandByCountry(@Param("country") String country);

    @Query("Select cmd From Command cmd Where cmd.stateCommand = :stateCommand")
    List<Command> findCommandByStateCommand(@Param("stateCommand") StateCommand stateCommand);

    @Query("Select cmd From Command cmd Where cmd.creationDate = :creationDate")
    List<Command> findCommandByCreationDate(@Param("creationDate") LocalDateTime creationDate);

    @Query("Select cmd.nameCommand, cmd.creationDate From Command cmd Where cmd.creationDate = :creationDate")
    List<Command> findCommandByNameCommandAndCreationDate(@Param("creationDate") LocalDateTime creationDate);

    @Query("Select cmd.nameCommand, cmd.stateCommand From Command cmd Where cmd.stateCommand = :stateCommand")
    List<Command> findCommandByNameCommandAndStateCommand(@Param("stateCommand") StateCommand stateCommand);

    @Query("Select cmd.nameCommand, cmd.creationDate, cmd.stateCommand From Command cmd Where (cmd.creationDate = :creationDate) And (cmd.stateCommand = :stateCommand)")
    List<Command> findCommandByCreationDateAndStateCommand(@Param("creationDate") LocalDateTime creationDate, @Param("stateCommand") StateCommand stateCommand);

    @Query("Select cmd.nameCommand, cmd.phoneNumber From Command cmd Where cmd.phoneNumber is not null")
    List<Command> findCommandByNameCommandAndPhoneNumber();

}
