package portfolioshop.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);

    Category findByName(String categoryName);

    @Query("select c from Category c where c.parent is null")
    List<Category> findByMainCategory();

    @Query("select c from Category c where c.parent is not null")
    List<Category> findBySubCategory();

    @Query("select c.name from Category c where c.parent.name =:mainName")
    List<String> findSubCategoryFromMainCategory(String mainName);
}
