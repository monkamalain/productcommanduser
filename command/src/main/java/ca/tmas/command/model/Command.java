package ca.tmas.command.model;

import ca.tmas.command.utility.StateCommand;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
public class Command extends EntityWithUUID implements Serializable {

    static final long serialVersionUID = 1L;

    @Column(name = "refCommand", nullable = false, unique = true)
    String refCommand;

    @Column(name = "nameCommand", nullable = false)
    String nameCommand;

    @Embedded
    @AttributeOverrides(value={
            @AttributeOverride(name = "addressLine1", column = @Column(name = "addressLine1", nullable = false, insertable = true, updatable = true, length = 5)),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "addressLine2", nullable = false, insertable = true, updatable = true)),
            @AttributeOverride(name = "addressLine3", column = @Column(name = "addressLine3", nullable = false, insertable = true, updatable = true, length = 7)),
            @AttributeOverride(name = "cityAddress", column = @Column(name = "city", nullable = false, insertable = true, updatable = true)),
            @AttributeOverride(name = "provinceAddress", column = @Column(name = "province", nullable = false, insertable = true, updatable = true)),
            @AttributeOverride(name = "countryAddress", column = @Column(name = "country", nullable = false, insertable = true, updatable = true))
    })
    Address address;

    @Column(name = "phoneNumber", nullable = false, length = 15)
    String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creationDate", nullable = false)
    LocalDateTime creationDate;

    @Column(name = "stateCommand", nullable = false)
    @Enumerated(EnumType.STRING)
    StateCommand stateCommand;

}
