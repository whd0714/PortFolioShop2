package portfolioshop.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.item.Item;
import portfolioshop.itemCategory.ItemCategoryService;
import portfolioshop.productSetting.dto.CategoryDelDto;
import portfolioshop.productSetting.dto.CategoryNameDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemCategoryService itemCategoryService;

    public void confirm(String categoryName, Item item) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            Category category2 = new Category(categoryName);
            categoryRepository.save(category2);
            itemCategoryService.createItemCategory(category2, item);

        } else {
            itemCategoryService.createItemCategory(category, item);
        }
    }


    public void saveSubCategory(CategoryNameDto categoryNameDto) {
        Category main = categoryRepository.findByName(categoryNameDto.getMainName());

        Category sub = new Category(categoryNameDto.getSubName());

        sub.changeCategory(main);

        categoryRepository.save(sub);
    }

    public void saveMainCategory(String name) {
        if(!categoryRepository.existsByName(name)) {
            Category category = new Category(name);
            categoryRepository.save(category);
        }
    }

    public void delCategory(CategoryDelDto categoryDelDto) {
        for(int i = 0; i < categoryDelDto.getDelNames().size(); i++) {
            Category category = categoryRepository.findByName(categoryDelDto.getDelNames().get(i));
            if(category != null) {
                categoryRepository.delete(category);
            }
        }
    }

    public List<String> findSubCategory(String mainName) {
        List<String> subCategoryNames = categoryRepository.findSubCategoryFromMainCategory(mainName);

        return subCategoryNames;
    }
}
