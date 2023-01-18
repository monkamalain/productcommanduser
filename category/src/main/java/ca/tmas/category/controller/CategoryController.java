package ca.tmas.category.controller;

import ca.tmas.category.dto.CategoryDto;
import ca.tmas.category.service.CategoryService;
import ca.tmas.category.utility.StatusCategory;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@RefreshScope
@EnableFeignClients(basePackages = { "ca.tmas.category" })
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/createCategory")
    synchronized public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            return new ResponseEntity<CategoryDto>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return new ResponseEntity<CategoryDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCategoryByRef/{ref}")
    synchronized public ResponseEntity<Optional<CategoryDto>> getCategoryByRef(@PathVariable(value = "ref") String ref) {
        if(categoryService.getCategoryByRef(ref).isPresent()) {
            return new ResponseEntity<Optional<CategoryDto>>(categoryService.getCategoryByRef(ref), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CategoryDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllRefCategories")
    synchronized public ResponseEntity<Set<String>> getAllRefCategories() {
        if(categoryService.getAllRefCategories().isEmpty()) {
            return new ResponseEntity<Set<String>>(Collections.emptySet(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Set<String>>(categoryService.getAllRefCategories(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCategories")
    synchronized public ResponseEntity<List<CategoryDto>> getAllCategories() {
        if(categoryService.getAllCategories().isEmpty()) {
            return new ResponseEntity<List<CategoryDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCategoriesByNameCategory/{nameCategory}")
    synchronized public ResponseEntity<List<CategoryDto>> getAllCategoriesByNameCategory(@PathVariable(value = "nameCategory") String nameCategory) {
        if(categoryService.getAllCategoriesByNameCategory(nameCategory).isEmpty()) {
            return new ResponseEntity<List<CategoryDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategoriesByNameCategory(nameCategory), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCategoriesByTotalCategory/{totalCategory}")
    synchronized public ResponseEntity<List<CategoryDto>> getAllCategoriesByTotalCategory(@PathVariable(value = "totalCategory") int totalCategory) {
        if(categoryService.getAllCategoriesByTotalCategory(totalCategory).isEmpty()) {
            return new ResponseEntity<List<CategoryDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategoriesByTotalCategory(totalCategory), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCategoriesByStatusCategory/{statusCategory}")
    synchronized public ResponseEntity<List<CategoryDto>> getAllCategoriesByStatusCategory(@PathVariable(value = "statusCategory") StatusCategory statusCategory) {
        if(categoryService.getAllCategoriesByStatusCategory(statusCategory).isEmpty()) {
            return new ResponseEntity<List<CategoryDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategoriesByStatusCategory(statusCategory), HttpStatus.OK);
        }
    }

    @PatchMapping("/updatePatiallyCategory/{ref}")
    synchronized public ResponseEntity<Optional<CategoryDto>> updatePatiallyCategory(@PathVariable(value = "ref") String ref, @RequestBody CategoryDto categoryDto) {
        if(categoryService.updatePatiallyCategory(ref, categoryDto).isPresent()) {
            return new ResponseEntity<Optional<CategoryDto>>(categoryService.updatePatiallyCategory(ref, categoryDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CategoryDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTotallyCategory/{ref}")
    synchronized public ResponseEntity<Optional<CategoryDto>> updateTotallyCategory(@PathVariable(value = "ref") String ref, @RequestBody CategoryDto categoryDto) {
        if(categoryService.updateTotallyCategory(ref, categoryDto).isPresent()) {
            return new ResponseEntity<Optional<CategoryDto>>(categoryService.updateTotallyCategory(ref, categoryDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CategoryDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCategory/{ref}")
    synchronized public ResponseEntity<Optional<CategoryDto>> deleteCategory(@PathVariable(value = "ref") String ref) {
        if(categoryService.deleteCategory(ref).isPresent()) {
            return new ResponseEntity<Optional<CategoryDto>>(categoryService.deleteCategory(ref), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<CategoryDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

}
