package ca.tmas.category.service.impl;

import ca.tmas.category.dto.CategoryDto;
import ca.tmas.category.model.Category;
import ca.tmas.category.repository.CategoryRepository;
import ca.tmas.category.service.CategoryService;
import ca.tmas.category.utility.StatusCategory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final long CSTCAT;

    private static final String REFCAT;

    private ModelMapper mapper;

    private CategoryRepository categoryRepository;

    static {
        CSTCAT = 44444L;
        REFCAT = "cat-";
    }

    @Transactional
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat;
        String ref;
        if(categoryRepository.findAllRefCategories().isEmpty()) {
            ref = REFCAT + Long.toHexString(Long.MAX_VALUE % CSTCAT);
        } else {
            String maxRef = categoryRepository.findAllRefCategories().stream().map(x -> x.substring(4)).collect(Collectors.toSet()).stream().max(String::compareTo).get();
            Long nextValueRef = Long.valueOf(maxRef, 16) + 1;
            ref = REFCAT + Long.toHexString(nextValueRef);
        }
        try {
            cat = mapper.map(categoryDto, Category.class);
            cat.setRefCategory(ref.toUpperCase());
            cat.setStatusCategory(StatusCategory.VALIDATED);
            return mapper.map(categoryRepository.save(cat), CategoryDto.class);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Bad request");
        }
    }

    @Override
    public Optional<CategoryDto> getCategoryByRef(String ref) {
        return Optional.ofNullable(mapper.map(categoryRepository.findCategoryByRef(ref).get(), CategoryDto.class));
    }

    @Override
    public Set<String> getAllRefCategories() {
        return (categoryRepository.findAllRefCategories().isEmpty())? Collections.emptySet(): categoryRepository.findAllRefCategories();
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        if(categoryRepository.findAll().isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            categoryRepository.findAll().forEach(cat -> categoryDtoList.add(mapper.map(cat, CategoryDto.class)));
            return categoryDtoList;
        }
    }

    @Override
    public List<CategoryDto> getAllCategoriesByNameCategory(String nameCategory) {
        if(categoryRepository.findAllCategoriesByNameCategory(nameCategory).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            categoryRepository.findAllCategoriesByNameCategory(nameCategory).forEach(cat -> categoryDtoList.add(mapper.map(cat, CategoryDto.class)));
            return categoryDtoList;
        }
    }

    @Override
    public List<CategoryDto> getAllCategoriesByTotalCategory(int totalCategory) {
        if(categoryRepository.findCategoriesByTotalCategory(totalCategory).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            categoryRepository.findCategoriesByTotalCategory(totalCategory).forEach(cat -> categoryDtoList.add(mapper.map(cat, CategoryDto.class)));
            return categoryDtoList;
        }
    }

    @Override
    public List<CategoryDto> getAllCategoriesByStatusCategory(StatusCategory statusCategory) {
        if(categoryRepository.findCategoriesByStatusCategory(statusCategory).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            categoryRepository.findCategoriesByStatusCategory(statusCategory).forEach(cat -> categoryDtoList.add(mapper.map(cat, CategoryDto.class)));
            return categoryDtoList;
        }
    }

    @Transactional
    @Override
    public Optional<CategoryDto> updatePatiallyCategory(String ref, CategoryDto categoryDto) {
        if((categoryDto != null) && (categoryRepository.findCategoryByRef(ref).isPresent()) && (categoryRepository.findCategoryByRef(ref).get().getStatusCategory().equals(StatusCategory.VALIDATED))) {
            Category currentCategory = mapper.map(categoryDto, Category.class);
            Category dbCategory = categoryRepository.findCategoryByRef(ref).get();
            dbCategory.setRefCategory(ref);
            mapper.getConfiguration().setSkipNullEnabled(true);
            mapper.map(currentCategory, dbCategory);
            Category updateCategory = categoryRepository.save(dbCategory);
            return Optional.ofNullable(mapper.map(updateCategory, CategoryDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<CategoryDto> updateTotallyCategory(String ref, CategoryDto categoryDto) {
        if((categoryDto != null) && (categoryRepository.findCategoryByRef(ref).isPresent())) {
            Category currentCategory = mapper.map(categoryDto, Category.class);
            currentCategory.setId(categoryRepository.findCategoryByRef(ref).get().getId());
            currentCategory.setRefCategory(categoryRepository.findCategoryByRef(ref).get().getRefCategory());
            Category updateCategory = categoryRepository.save(currentCategory);
            return Optional.ofNullable(mapper.map(updateCategory, CategoryDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<CategoryDto> deleteCategory(String ref) {
        if((categoryRepository.findCategoryByRef(ref).isPresent()) && (categoryRepository.findCategoryByRef(ref).get().getStatusCategory().equals(StatusCategory.VALIDATED))) {
            Category cat = categoryRepository.findCategoryByRef(ref).get();
            cat.setStatusCategory(StatusCategory.UNVALIDATED);
            return Optional.of(mapper.map(categoryRepository.save(cat), CategoryDto.class));
        } else {
            return Optional.empty();
        }
    }
}
