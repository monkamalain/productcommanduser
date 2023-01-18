package ca.tmas.command.dto;

import ca.tmas.command.utility.StateCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CommandDto {

    String refCommand;

    @NotEmpty(message = "Name of command may not be empty")
    String nameCommand;

    @NotEmpty(message = "House number may not be empty")
    String addressLine1;

    @NotEmpty(message = "Street may not be empty")
    String addressLine2;

    @NotEmpty(message = "Postal code may not be empty")
    String addressLine3;

    @NotEmpty(message = "City may not be empty")
    String cityAddress;

    @NotEmpty(message = "Province may not be empty")
    String provinceAddress;

    @NotEmpty(message = "Country may not be empty")
    String countryAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime creationDate;

    StateCommand stateCommand;

}
