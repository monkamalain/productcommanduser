package ca.tmas.category.model;

import ca.tmas.category.utility.StatusCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
public class Category extends EntityWithUUID implements Serializable {

    static final long serialVersionUID = 1L;

    @Column(name = "refCategory", nullable = false, unique = true)
    String refCategory;

    @Column(name = "nameCategory", nullable = false)
    String nameCategory;

    @Column(name = "totalCategory", nullable = false)
    int totalCategory;

    @Column(name = "statusCategory", nullable = false)
    @Enumerated(EnumType.STRING)
    StatusCategory statusCategory;

}
