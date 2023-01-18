package ca.tmas.command.controller;

import ca.tmas.command.dto.CommandDto;
import ca.tmas.command.service.CommandService;
import ca.tmas.command.utility.StateCommand;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/commands")
@AllArgsConstructor
@RefreshScope
@EnableFeignClients(basePackages = { "ca.tmas.command" })
public class CommandController {

    private CommandService commandService;

    @PostMapping("/createCommand")
    synchronized public ResponseEntity<CommandDto> createCommand(@RequestBody CommandDto commandDto) {
        try {
            return new ResponseEntity<CommandDto>(commandService.createCommand(commandDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return new ResponseEntity<CommandDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCommandByRef/{ref}")
    synchronized public ResponseEntity<Optional<CommandDto>> getCommandByRef(@PathVariable(value = "ref") String ref) {
        if(commandService.getCommandByRef(ref).isPresent()) {
            return new ResponseEntity<Optional<CommandDto>>(commandService.getCommandByRef(ref), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CommandDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllRefCommands")
    synchronized public ResponseEntity<Set<String>> getAllRefCommands() {
        if(commandService.getAllRefCommands().isEmpty()) {
            return new ResponseEntity<Set<String>>(Collections.emptySet(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Set<String>>(commandService.getAllRefCommands(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCommands")
    synchronized public ResponseEntity<List<CommandDto>> getAllCommands() {
        if(commandService.getAllCommands().isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getAllCommands(), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByNameCommand/{nameCommand}")
    synchronized public ResponseEntity<Optional<CommandDto>> getCommandByNameCommand(@PathVariable(value = "nameCommand") String nameCommand) {
        if(commandService.getCommandByNameCommand(nameCommand).isPresent()) {
            return new ResponseEntity<Optional<CommandDto>>(commandService.getCommandByNameCommand(nameCommand), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CommandDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCommandByHouseNumber/{houseNumber}")
    synchronized public ResponseEntity<Optional<CommandDto>> getCommandByHouseNumber(@PathVariable(value = "houseNumber") String houseNumber) {
        if(commandService.getCommandByHouseNumber(houseNumber).isPresent()) {
            return new ResponseEntity<Optional<CommandDto>>(commandService.getCommandByHouseNumber(houseNumber), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CommandDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCommandByStreet/{street}")
    synchronized public ResponseEntity<Optional<CommandDto>> getCommandByStreet(@PathVariable(value = "street") String street) {
        if(commandService.getCommandByStreet(street).isPresent()) {
            return new ResponseEntity<Optional<CommandDto>>(commandService.getCommandByStreet(street), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CommandDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCommandByZipCode/{zipCode}")
    synchronized public ResponseEntity<Optional<CommandDto>> getCommandByZipCode(@PathVariable(value = "zipCode") String zipCode) {
        if(commandService.getCommandByZipCode(zipCode).isPresent()) {
            return new ResponseEntity<Optional<CommandDto>>(commandService.getCommandByZipCode(zipCode), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CommandDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCommandByCity/{city}")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByCity(@PathVariable(value = "city") String city) {
        if(commandService.getCommandByCity(city).isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByCity(city), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByProvince/{province}")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByProvince(@PathVariable(value = "province") String province) {
        if(commandService.getCommandByProvince(province).isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByProvince(province), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByCountry/{country}")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByCountry(@PathVariable(value = "country") String country) {
        if(commandService.getCommandByCountry(country).isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByCountry(country), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByStateCommand/{stateCommand}")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByStateCommand(@PathVariable(value = "stateCommand") StateCommand stateCommand) {
        if(commandService.getCommandByStateCommand(stateCommand).isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByStateCommand(stateCommand), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByCreationDate/{creationDate}")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByCreationDate(@PathVariable(value = "creationDate") LocalDateTime creationDate) {
        if(commandService.getCommandByCreationDate(creationDate).isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByCreationDate(creationDate), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByNameCommandAndCreationDate/{creationDate}")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByNameCommandAndCreationDate(@PathVariable(value = "creationDate") LocalDateTime creationDate) {
        if(commandService.getCommandByNameCommandAndCreationDate(creationDate).isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByNameCommandAndCreationDate(creationDate), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByNameCommandAndStateCommand/{stateCommand}")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByNameCommandAndStateCommand(@PathVariable(value = "stateCommand") StateCommand stateCommand) {
        if(commandService.getCommandByNameCommandAndStateCommand(stateCommand).isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByNameCommandAndStateCommand(stateCommand), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByCreationDateAndStateCommand/{creationDate}/{stateCommand}")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByCreationDateAndStateCommand(@PathVariable(value = "creationDate") LocalDateTime creationDate, @PathVariable(value = "stateCommand") StateCommand stateCommand) {
        if(commandService.getCommandByCreationDateAndStateCommand(creationDate, stateCommand).isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByCreationDateAndStateCommand(creationDate, stateCommand), HttpStatus.OK);
        }
    }

    @GetMapping("/getCommandByNameCommandAndPhoneNumber")
    synchronized public ResponseEntity<List<CommandDto>> getCommandByNameCommandAndPhoneNumber() {
        if(commandService.getCommandByNameCommandAndPhoneNumber().isEmpty()) {
            return new ResponseEntity<List<CommandDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CommandDto>>(commandService.getCommandByNameCommandAndPhoneNumber(), HttpStatus.OK);
        }
    }

    @PatchMapping("/updatePatiallyCommand/{ref}")
    synchronized public ResponseEntity<Optional<CommandDto>> updatePatiallyCommand(@PathVariable(value = "ref") String ref, @RequestBody CommandDto commandDto) {
        if(commandService.updatePatiallyCommand(ref, commandDto).isPresent()) {
            return new ResponseEntity<Optional<CommandDto>>(commandService.updatePatiallyCommand(ref, commandDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CommandDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTotallyCommand/{ref}")
    synchronized public ResponseEntity<Optional<CommandDto>> updateTotallyCommand(@PathVariable(value = "ref") String ref, @RequestBody CommandDto commandDto) {
        if(commandService.updateTotallyCommand(ref, commandDto).isPresent()) {
            return new ResponseEntity<Optional<CommandDto>>(commandService.updateTotallyCommand(ref, commandDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CommandDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCommandByRef/{ref}")
    synchronized public ResponseEntity<Optional<CommandDto>> deleteCommandByRef(@PathVariable(value = "ref") String ref) {
        if(commandService.deleteCommandByRef(ref).isPresent()) {
            return new ResponseEntity<Optional<CommandDto>>(commandService.deleteCommandByRef(ref), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CommandDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

}
