package portfolioshop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import portfolioshop.brand.Brand;
import portfolioshop.item.dto.queryDto.GoodsCategoryListSearchCondition;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.item.searchQuery.SettingMainCondition;
import portfolioshop.main.dto.MainSearchDto;
import portfolioshop.main.dto.TagSearchDto;

import java.util.List;

public interface ItemSearchRepository {

    Page<Item> searchProductSetting(SettingMainCondition condition, Pageable pageable);

    Page<Item> findAllByPage(Pageable pageable);

    Page<Item> findAllItemByCategory(ItemCategoryCondition condition, Pageable pageable);

    Page<Item> findAllItemInSettingForm(Pageable pageable);

    List<Item> findNewItemByCategory();

    //@Query("select distinct i from Item i join fetch i.brand b join i.itemCategories ic join ic.category c where c.id = :categoryId")
    Page<Item> findItemFetchJoin(GoodsCategoryListSearchCondition condition, Long categoryId, Pageable pageable);

    Page<Item> findItemFetchJoin2(GoodsCategoryListSearchCondition condition, Long categoryId, Pageable pageable);

    List<Item> findItemFetchJoinNoConditions(Long categoryId);

    List<Item> findItemFetchJoinNoConditions2(Long categoryId);


    Page<Item> findItemFromQuery(MainSearchDto mainSearchDto, Pageable pageable);

    List<Item> findItemFromBestView();

    List<Item> findItemFromBestSale();

    List<Item> findItemFromNewItem();


}
