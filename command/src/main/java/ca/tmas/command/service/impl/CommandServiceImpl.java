package ca.tmas.command.service.impl;

import ca.tmas.command.dto.CommandDto;
import ca.tmas.command.model.Command;
import ca.tmas.command.repository.CommandRepository;
import ca.tmas.command.service.CommandService;
import ca.tmas.command.utility.StateCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@AllArgsConstructor
public class CommandServiceImpl implements CommandService {

    private static final long CSTCMD;

    private static final String REFCMD;

    private ModelMapper mapper;

    private CommandRepository commandRepository;

    static {
        CSTCMD = 33333L;
        REFCMD = "cmd-";
    }

    @Transactional
    @Override
    public CommandDto createCommand(CommandDto commandDto) {
        Command cmd;
        String ref;
        if(commandRepository.findAllRefCommand().isEmpty()) {
            ref = REFCMD + Long.toHexString(Long.MAX_VALUE % CSTCMD);
        } else {
            String maxRef = commandRepository.findAllRefCommand().stream().map(x -> x.substring(4)).collect(Collectors.toSet()).stream().max(String::compareTo).get();
            long nextValueRef = Long.valueOf(maxRef, 16) + 1;
            ref = REFCMD + Long.toHexString(nextValueRef);
        }
        try {
            cmd = mapper.map(commandDto, Command.class);
            cmd.setRefCommand(ref.toUpperCase());
            cmd.setCreationDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            cmd.setStateCommand(StateCommand.VALIDATED);
            return mapper.map(commandRepository.save(cmd), CommandDto.class);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Bad request");
        }
    }

    @Override
    public Optional<CommandDto> getCommandByRef(String ref) {
        return Optional.ofNullable(mapper.map(commandRepository.findCommandByRef(ref).get(), CommandDto.class));
    }

    @Override
    public Set<String> getAllRefCommands() {
        return (commandRepository.findAllRefCommand().isEmpty())? Collections.emptySet(): commandRepository.findAllRefCommand();
    }

    @Override
    public List<CommandDto> getAllCommands() {
        if(commandRepository.findAll().isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findAll().forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public Optional<CommandDto> getCommandByNameCommand(String nameCommand) {
        return Optional.ofNullable(mapper.map(commandRepository.findCommandByNameCommand(nameCommand).get(), CommandDto.class));
    }

    @Override
    public Optional<CommandDto> getCommandByHouseNumber(String houseNumber) {
        return Optional.ofNullable(mapper.map(commandRepository.findCommandByHouseNumber(houseNumber).get(), CommandDto.class));
    }

    @Override
    public Optional<CommandDto> getCommandByStreet(String street) {
        return Optional.ofNullable(mapper.map(commandRepository.findCommandByStreet(street).get(), CommandDto.class));
    }

    @Override
    public Optional<CommandDto> getCommandByZipCode(String zipCode) {
        return Optional.ofNullable(mapper.map(commandRepository.findCommandByZipCode(zipCode).get(), CommandDto.class));
    }

    @Override
    public List<CommandDto> getCommandByCity(String city) {
        if(commandRepository.findCommandByCity(city).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByCity(city).forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public List<CommandDto> getCommandByProvince(String province) {
        if(commandRepository.findCommandByProvince(province).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByProvince(province).forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public List<CommandDto> getCommandByCountry(String country) {
        if(commandRepository.findCommandByCountry(country).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByCountry(country).forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public List<CommandDto> getCommandByStateCommand(StateCommand stateCommand) {
        if(commandRepository.findCommandByStateCommand(stateCommand).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByStateCommand(stateCommand).forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public List<CommandDto> getCommandByCreationDate(LocalDateTime creationDate) {
        if(commandRepository.findCommandByCreationDate(creationDate).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByCreationDate(creationDate).forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public List<CommandDto> getCommandByNameCommandAndCreationDate(LocalDateTime creationDate) {
        if(commandRepository.findCommandByNameCommandAndCreationDate(creationDate).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByNameCommandAndCreationDate(creationDate).forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public List<CommandDto> getCommandByNameCommandAndStateCommand(StateCommand stateCommand) {
        if(commandRepository.findCommandByNameCommandAndStateCommand(stateCommand).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByNameCommandAndStateCommand(stateCommand).forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public List<CommandDto> getCommandByCreationDateAndStateCommand(LocalDateTime creationDate, StateCommand stateCommand) {
        if(commandRepository.findCommandByCreationDateAndStateCommand(creationDate, stateCommand).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByCreationDateAndStateCommand(creationDate, stateCommand).forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Override
    public List<CommandDto> getCommandByNameCommandAndPhoneNumber() {
        if(commandRepository.findCommandByNameCommandAndPhoneNumber().isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CommandDto> commandDtoList = new ArrayList<>();
            commandRepository.findCommandByNameCommandAndPhoneNumber().forEach(cat -> commandDtoList.add(mapper.map(cat, CommandDto.class)));
            return commandDtoList;
        }
    }

    @Transactional
    @Override
    public Optional<CommandDto> updatePatiallyCommand(String ref, CommandDto commandDto) {
        if((commandDto != null) && (commandRepository.findCommandByRef(ref).isPresent()) && (commandRepository.findCommandByRef(ref).get().getStateCommand().equals(StateCommand.VALIDATED))) {
            Command currentCommand = mapper.map(commandDto, Command.class);
            Command dbCommand = commandRepository.findCommandByRef(ref).get();
            dbCommand.setRefCommand(ref);
            mapper.getConfiguration().setSkipNullEnabled(true);
            mapper.map(currentCommand, dbCommand);
            Command updateCommand = commandRepository.save(dbCommand);
            return Optional.ofNullable(mapper.map(updateCommand, CommandDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<CommandDto> updateTotallyCommand(String ref, CommandDto commandDto) {
        if((commandDto != null) && (commandRepository.findCommandByRef(ref).isPresent())) {
            Command currentCommand = mapper.map(commandDto, Command.class);
            currentCommand.setId(commandRepository.findCommandByRef(ref).get().getId());
            currentCommand.setRefCommand(commandRepository.findCommandByRef(ref).get().getRefCommand());
            Command updateCommand = commandRepository.save(currentCommand);
            return Optional.ofNullable(mapper.map(updateCommand, CommandDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<CommandDto> deleteCommandByRef(String ref) {
        if((commandRepository.findCommandByRef(ref).isPresent()) && (commandRepository.findCommandByRef(ref).get().getStateCommand().equals(StateCommand.VALIDATED))) {
            Command cmd = commandRepository.findCommandByRef(ref).get();
            cmd.setStateCommand(StateCommand.UNVALIDATED);
            return Optional.of(mapper.map(commandRepository.save(cmd), CommandDto.class));
        } else {
            return Optional.empty();
        }
    }
}
