package ca.tmas.category.service;


import ca.tmas.category.dto.CategoryDto;
import ca.tmas.category.utility.StatusCategory;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    Optional<CategoryDto> getCategoryByRef(String ref);

    Set<String> getAllRefCategories();

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByNameCategory(String nameCategory);

    List<CategoryDto> getAllCategoriesByTotalCategory(int totalCategory);

    List<CategoryDto> getAllCategoriesByStatusCategory(StatusCategory statusCategory);

    Optional<CategoryDto> updatePatiallyCategory(String ref, CategoryDto categoryDto);

    Optional<CategoryDto> updateTotallyCategory(String ref, CategoryDto categoryDto);

    Optional<CategoryDto> deleteCategory(String ref);

}
