package ca.tmas.category.dto;

import ca.tmas.category.utility.StatusCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CategoryDto {

    String refCategory;

    @NotEmpty(message = "Name of Category may not be empty")
    String nameCategory;

    @NotEmpty(message = "Total Category may not be empty")
    int totalCategory;

    StatusCategory statusCategory;

}
