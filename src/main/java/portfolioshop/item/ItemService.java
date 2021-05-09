package portfolioshop.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.brand.Brand;
import portfolioshop.brand.BrandRepository;
import portfolioshop.brand.BrandService;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.category.CategoryService;
import portfolioshop.goods.GoodsService;
import portfolioshop.goods.enumType.DisplayStatus;
import portfolioshop.item.dto.DisplayStatusDto;
import portfolioshop.item.enumType.Gender;
import portfolioshop.itemCategory.ItemCategory;
import portfolioshop.itemCategory.ItemCategoryRepository;
import portfolioshop.itemCategory.ItemCategoryService;
import portfolioshop.itemTag.ItemTag;
import portfolioshop.itemTag.ItemTagRepository;
import portfolioshop.itemTag.ItemTagService;
import portfolioshop.productSetting.dto.ProductAddDto;
import portfolioshop.productSetting.dto.ProductUpdateDto;
import portfolioshop.tag.Tag;
import portfolioshop.tag.TagRepository;
import portfolioshop.tag.TagService;
import portfolioshop.tag.enumType.TagType;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final ItemCategoryService itemCategoryService;
    private final ItemTagService itemTagService;
    private final TagService tagService;

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

        tagService.addNewTag(productAddDto.getItemTag(), item);

        /*String[] tags = productAddDto.getItemTag().split("#");
        if(tags.length != 0)  {
            for(int i = 1; i < tags.length; i++) {
                Tag tag = new Tag("#"+tags[i], TagType.HASH);

                ItemTag itemTag = new ItemTag();
                itemTag.changeItem(item);
                itemTag.changeTag(tag);

                itemTagRepository.save(itemTag);

                tagRepository.save(tag);
            }
        }*/
    }

    public void updateProduct(ProductUpdateDto productUpdateDto, Long itemId) throws IOException {


        Item item = itemRepository.findByUpdateProductByItemId(itemId);

        itemCategoryService.updateItemCategory(item.getItemCategories().get(0),productUpdateDto, item);
        itemTagService.updateTag(productUpdateDto, item);

       /* if (productUpdateDto.getItemBrandName() != item.getBrand().getBrandName()) {

        }*/

        Brand brand = brandRepository.findByBrandName(productUpdateDto.getItemBrandName());

        item.changeBrand(brand);

        goodsService.createGoods(item, productUpdateDto);
        goodsService.updateGoods(item, productUpdateDto);
        item.updateItem(productUpdateDto.getItemNo(), productUpdateDto.getItemName(), productUpdateDto.getItemNameEng(),
                productUpdateDto.getItemPrice(), productUpdateDto.getSeason(), productUpdateDto.getGender(), productUpdateDto.getSubDescription(), productUpdateDto.getDescription());
        System.out.println("ccccccccccccc" + productUpdateDto.getItemImage());
        if(productUpdateDto.getItemImage().getSize() != 0) {
            byte[] bytes = productUpdateDto.getItemImage().getBytes();
            byte[] encode = Base64.getEncoder().encode(bytes);
            String img = new String(encode, "UTF-8");
            item.changeImg(img);
        }




        /*itemTags = item.getItemTags().stream()
                .map(itemTag -> new ItemTagDto(itemTag))
                .collect(Collectors.toList());

        itemCategories = item.getItemCategories().stream()
                .map(itemCategory -> new ItemCategoryDto(itemCategory))
                .collect(Collectors.toList());*/



    }

    public void updateDisplay(DisplayStatusDto displayStatusDto) {
        Optional<Item> byId = itemRepository.findById(displayStatusDto.getItemId());
        byId.ifPresent(item -> {
            if (DisplayStatus.진열안함.toString().equals(displayStatusDto.getDisplay())){
                item.changeDisplay(DisplayStatus.진열안함);
            } else if(DisplayStatus.진열함.toString().equals(displayStatusDto.getDisplay())) {
                item.changeDisplay(DisplayStatus.진열함);
            }
        });
    }

    public void upDateView(Item item) {
        item.upView();
    }

   /* @Data
    static class CategoryDto {
        private String Name;

        public CategoryDto(Category category) {
            this.Name = category.getParent().getName();
        }
    }

    @Data
    static class ItemCategoryDto {

        private String categoryName;
        private CategoryDto categoryParent;

        public ItemCategoryDto(ItemCategory itemCategory) {
            this.categoryName = itemCategory.getCategory().getName();
            categoryParent = new CategoryDto(itemCategory.getCategory());

        }
    }

    @Data
    static class UpdateItemCategoryDto {

        private Category parentCategory;
        private Category childCategory;

        private String categoryName;
        private CategoryDto categoryParent;

        public UpdateItemCategoryDto(ItemCategory itemCategory) {
            this.categoryName = itemCategory.getCategory().getName();
            categoryParent = new CategoryDto(itemCategory.getCategory());

        }
    }


    @Data
    static class ItemTagDto {

        private String tagName;

        public ItemTagDto(ItemTag itemTag) {
            this.tagName = itemTag.getTag().getTagName();
        }
    }*/

    public void ex () {
        Page<Item> itemPrice = itemRepository.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "itemPrice")));


    }
}
