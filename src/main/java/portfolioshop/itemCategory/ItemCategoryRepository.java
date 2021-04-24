package portfolioshop.itemCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import portfolioshop.category.Category;

import java.util.List;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

    /*@Query("select p.name, c.name from ItemCategory ic join fetch Item i on ic.item.id = :itemId join fetch Category c on ic.category.id = c.id join  fetch Category p on c.parent.id = p.id")
    String findByAllCategoryByItemId(Long itemId);*/


}
