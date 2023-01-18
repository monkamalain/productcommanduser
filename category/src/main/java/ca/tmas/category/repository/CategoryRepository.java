package ca.tmas.category.repository;

import ca.tmas.category.model.Category;
import ca.tmas.category.utility.StatusCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("Select cat From Category cat Where cat.refCategory like :ref")
    Optional<Category> findCategoryByRef(@Param("ref") String ref);

    @Query("Select cat.refCategory From Category cat Where cat.refCategory is not null")
    Set<String> findAllRefCategories();

    @Query("Select cat From Category cat Where cat.nameCategory like :name")
    List<Category> findAllCategoriesByNameCategory(@Param("name") String name);

    @Query("Select cat From Category cat Where cat.totalCategory = :totalCategory")
    List<Category> findCategoriesByTotalCategory(@Param("totalCategory") int totalCategory);

    @Query("Select cat From Category cat Where cat.statusCategory = :statusCategory")
    List<Category> findCategoriesByStatusCategory(@Param("statusCategory") StatusCategory statusCategory);

}

