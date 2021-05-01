package portfolioshop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.item.searchQuery.SettingMainCondition;

import java.util.List;

public interface ItemSearchRepository {

    Page<Item> searchProductSetting(SettingMainCondition condition, Pageable pageable);

    Page<Item> findAllByPage(Pageable pageable);

    Page<Item> findAllItemByCategory(ItemCategoryCondition condition, Pageable pageable);

    Page<Item> findAllItemInSettingForm(Pageable pageable);

    List<Item> findNewItemByCategory();

    //@Query("select distinct i from Item i join fetch i.brand b join i.itemCategories ic join ic.category c where c.id = :categoryId")
    Page<Item> findItemFetchJoin(Long categoryId, Pageable pageable);


}
