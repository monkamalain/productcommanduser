package ca.tmas.command.service;

import ca.tmas.command.dto.CommandDto;
import ca.tmas.command.utility.StateCommand;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CommandService {

    CommandDto createCommand(CommandDto commandDto);

    Optional<CommandDto> getCommandByRef(String ref);

    Set<String> getAllRefCommands();

    List<CommandDto> getAllCommands();

    Optional<CommandDto> getCommandByNameCommand(String nameCommand);

    Optional<CommandDto> getCommandByHouseNumber(String houseNumber);

    Optional<CommandDto> getCommandByStreet(String street);

    Optional<CommandDto> getCommandByZipCode(String zipCode);

    List<CommandDto> getCommandByCity(String city);

    List<CommandDto> getCommandByProvince(String province);

    List<CommandDto> getCommandByCountry(String country);

    List<CommandDto> getCommandByStateCommand(StateCommand stateCommand);

    List<CommandDto> getCommandByCreationDate(LocalDateTime creationDate);

    List<CommandDto> getCommandByNameCommandAndCreationDate(LocalDateTime creationDate);

    List<CommandDto> getCommandByNameCommandAndStateCommand(StateCommand stateCommand);

    List<CommandDto> getCommandByCreationDateAndStateCommand(LocalDateTime creationDate, StateCommand stateCommand);

    List<CommandDto> getCommandByNameCommandAndPhoneNumber();

    Optional<CommandDto> updatePatiallyCommand(String ref, CommandDto commandDto);

    Optional<CommandDto> updateTotallyCommand(String ref, CommandDto commandDto);

    Optional<CommandDto> deleteCommandByRef(String ref);

}
