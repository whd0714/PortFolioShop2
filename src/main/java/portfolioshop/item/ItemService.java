package portfolioshop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.brand.Brand;
import portfolioshop.brand.BrandRepository;
import portfolioshop.brand.BrandService;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.category.CategoryService;
import portfolioshop.goods.GoodsService;
import portfolioshop.itemCategory.ItemCategory;
import portfolioshop.itemCategory.ItemCategoryRepository;
import portfolioshop.itemCategory.ItemCategoryService;
import portfolioshop.itemTag.ItemTag;
import portfolioshop.itemTag.ItemTagRepository;
import portfolioshop.productSetting.dto.ProductAddDto;
import portfolioshop.tag.Tag;
import portfolioshop.tag.TagRepository;
import portfolioshop.tag.enumType.TagType;

import java.io.IOException;
import java.util.Base64;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ItemTagRepository itemTagRepository;
    private final GoodsService goodsService;

    public void saveProduct(ProductAddDto productAddDto) throws IOException {

        byte[] bytes = productAddDto.getItemImage().getBytes();
        byte[] encode = Base64.getEncoder().encode(bytes);
        String img = new String(encode, "UTF-8");

        Item item = new Item(productAddDto.getItemNo(), productAddDto.getItemName(), productAddDto.getItemNameEng(),
                productAddDto.getItemPrice(), productAddDto.getSeason(), productAddDto.getGender(),
                productAddDto.getSubDescription(), productAddDto.getDescription(), img);

        itemRepository.save(item);

        Brand brand = brandRepository.findByBrandName(productAddDto.getItemBrandName());
        item.changeBrand(brand);

        Category mainCategory = categoryRepository.findByName(productAddDto.getMainCategory());
        Category subCategory = categoryRepository.findByName(productAddDto.getSubCategory());

        ItemCategory itemCategory = new ItemCategory();
        itemCategory.changeItem(item);
        itemCategory.changeCategory(mainCategory);
        itemCategory.changeCategory(subCategory);
        itemCategoryRepository.save(itemCategory);

        String[] tags = productAddDto.getItemTag().split("#");
        System.out.println("aaaaaaaaaa" + productAddDto.getItemTag());
        System.out.println("aaaaaaaaaa" + tags);
        if(tags.length != 0)  {
            for(int i = 1; i < tags.length; i++) {
                System.out.println("aaaaaaaaaa" + tags[i]);
                Tag tag = new Tag("#"+tags[i], TagType.HASH);

                ItemTag itemTag = new ItemTag();
                itemTag.changeItem(item);
                itemTag.changeTag(tag);

                itemTagRepository.save(itemTag);

                tagRepository.save(tag);
            }
        }





    }


}
