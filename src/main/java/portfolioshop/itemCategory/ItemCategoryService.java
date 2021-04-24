package portfolioshop.itemCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.item.Item;
import portfolioshop.productSetting.dto.ProductUpdateDto;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;
    private final CategoryRepository categoryRepository;

    public void createItemCategory(Category category, Item item) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.changeCategory(category);
        itemCategory.changeItem(item);
        itemCategoryRepository.save(itemCategory);
    }

    public void updateItemCategory(ItemCategory itemCate, ProductUpdateDto productUpdateDto, Item item) {

        item.getItemCategories().removeIf(itemCategory -> itemCategory.getId().equals(itemCate.getId()));
        itemCategoryRepository.delete(itemCate);

        Category category = categoryRepository.findByName(productUpdateDto.getSubCategory());

        createItemCategory(category, item);

    }

}