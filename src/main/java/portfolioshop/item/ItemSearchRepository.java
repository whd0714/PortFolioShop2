package portfolioshop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.item.searchQuery.SettingMainCondition;

import java.util.List;

public interface ItemSearchRepository {

    Page<Item> searchProductSetting(SettingMainCondition condition, Pageable pageable);

    List<Item> searchProductSetting2(SettingMainCondition condition);

    Page<Item> findAllByPage(Pageable pageable);

    Page<Item> findAllItemByCategory(ItemCategoryCondition condition, Pageable pageable);

    Page<Item> findAllItemInSettingForm(Pageable pageable);

}
