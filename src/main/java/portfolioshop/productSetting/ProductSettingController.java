package portfolioshop.productSetting;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portfolioshop.brand.BrandRepository;
import portfolioshop.brand.BrandService;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.category.CategoryService;
import portfolioshop.goods.Goods;
import portfolioshop.goods.GoodsService;
import portfolioshop.goods.enumType.DisplayStatus;
import portfolioshop.goods.enumType.SaleStatus;
import portfolioshop.item.Item;
import portfolioshop.item.ItemRepository;
import portfolioshop.item.ItemService;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;
import portfolioshop.item.searchQuery.SettingMainCondition;
import portfolioshop.itemCategory.ItemCategoryRepository;
import portfolioshop.itemTag.ItemTag;
import portfolioshop.itemTag.ItemTagRepository;
import portfolioshop.productSetting.dto.*;
import portfolioshop.productSetting.validator.ItemAddValidator;
import portfolioshop.productSetting.validator.ItemUpdateValidator;

import javax.persistence.Lob;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProductSettingController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final BrandService brandService;
    private final BrandRepository brandRepository;
    private final ItemRepository itemRepository;
    private final ItemTagRepository itemTagRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final GoodsService goodsService;
    private final ItemAddValidator itemAddValidator;
    private final ItemUpdateValidator itemUpdateValidator;

    @InitBinder("productAddDto")
    public void productAddValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(itemAddValidator);
    }

    @InitBinder("productUpdateDto")
    public void productUpdateValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(itemUpdateValidator);
    }

    @GetMapping("/setting")
    public String settingForm(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

        PageRequest of = PageRequest.of(page, 2);

        //List<Item> all = itemRepository.findAll();
        //PageRequest of = PageRequest.of(0, 5);
        //Page<Item> all = itemRepository.findAllByPage(of);
        //List<Item> items = all.stream().collect(Collectors.toList());
        //List<Item> content = all.getContent();
        Page<Item> all = itemRepository.findAllItemInSettingForm(of);
        List<Item> content = all.getContent();

        List<ItemSettingMainDto> items = content.stream()
                .map(i -> new ItemSettingMainDto(i))
                .collect(Collectors.toList());

        if(items != null) {
            model.addAttribute("pages", all);
            model.addAttribute("maxPage", 3);
            model.addAttribute("items2", items);
            model.addAttribute("displays",DisplayStatus.values());
            model.addAttribute("saleStatus",SaleStatus.SALE);
        }
        return "setting/setting-form";
    }

    @Data
    static class ItemSettingMainDto {

        private Long itemId;
        private String itemNo;
        private String itemName;
        @Lob
        private String itemImage;
        private int itemPrice;
        private DisplayStatus displayStatus;
        private String brandName;
        private List<GoodsDto> goods;

        public ItemSettingMainDto(Item item) {
            this.itemId = item.getId();
            this.itemNo = item.getItemNo();
            this.itemImage = item.getItemImage();
            this.itemName = item.getItemName();
            this.itemPrice = item.getItemPrice();
            this.displayStatus = item.getDisplayStatus();
            this.brandName = item.getBrand().getBrandName();
            this.goods = item.getGoods().stream()
                    .map(g -> new GoodsDto(g))
                    .collect(Collectors.toList());
        }
    }

    @Data
    static class GoodsDto {
        private SaleStatus saleStatus;
        public GoodsDto(Goods goods) {
            this.saleStatus = goods.getSaleStatus();
        }
    }

    @GetMapping("/setting/add")
    public String settingAdd(Model model) {
        List<Category> all = categoryRepository.findAll();

        List<Category> collect = all.stream().collect(Collectors.toList());

        List<String> brandNames = brandRepository.findAllBrandName();

        model.addAttribute(new ProductAddDto());
        if(collect != null) {
            model.addAttribute("collect", collect);
        }
        if(brandNames != null) {
            model.addAttribute("brandNames", brandNames);
        }

        model.addAttribute("seasons",Season.values());
        model.addAttribute("genders",Gender.values());
        return "setting/setting-add";
    }

    @PostMapping("/setting/add")
    public String addProduct(@Valid ProductAddDto productAddDto, Errors errors, Model model) throws IOException {
        if(errors.hasErrors()) {
           List<Category> all = categoryRepository.findAll();

            List<Category> collect = all.stream().collect(Collectors.toList());

            //model.addAttribute(new ProductAddDto());
            //if(collect != null) {
                model.addAttribute("collect", collect);
          //  }

            List<String> brandNames = brandRepository.findAllBrandName();
            if(brandNames != null) {
                model.addAttribute("brandNames", brandNames);
            }

            model.addAttribute("seasons",Season.values());
            model.addAttribute("genders",Gender.values());
            return "setting/setting-add";
            //return "redirect:/setting/add";
        }
        itemService.saveProduct(productAddDto);

        return "redirect:/setting/add";
    }


    @GetMapping("/setting/category")
    public String settingCategory(Model model) {

        List<Category> all = categoryRepository.findAll();

        List<Category> collect = all.stream().collect(Collectors.toList());

        List<Category> mainCategory = new ArrayList<>();
        List<Category> subCategory = new ArrayList<>();


        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).getParent() != null) {
                subCategory.add(collect.get(i));
            } else {
                mainCategory.add(collect.get(i));
            }
        }

        model.addAttribute(new CategoryDelDto());
        model.addAttribute(new MainCategoryDto());
        model.addAttribute(new CategoryNameDto());
        model.addAttribute("mainCategory", mainCategory);
        model.addAttribute("subCategory", subCategory);

        return "setting/setting-category";
    }
/*    @PostMapping("/setting/category")
    public String addCategory22(@Valid CategoryNameDto categoryNameDto) {
        System.out.println(categoryNameDto);
        System.out.println("productAddDto");

        return "redirect:/setting/category";
    }*/

    @PostMapping("/category/addSubCategory")
    public String addCategory(@Valid CategoryNameDto categoryNameDto) {

        categoryService.saveSubCategory(categoryNameDto);

        return "redirect:/setting/category";
    }

    @PostMapping("/category/addMainCategory")
    public String addMainCategory(@Valid MainCategoryDto mainCategoryDto) {

        categoryService.saveMainCategory(mainCategoryDto.getName());

        return "redirect:/setting/category";
    }

    @PostMapping("/cateory/delCategory")
    public String delCategory(@Valid CategoryDelDto categoryDelDto) {

        categoryService.delCategory(categoryDelDto);

        return "redirect:/setting/category";
    }

    @PostMapping("/getSubCategory")
    @ResponseBody
    public List<String> getSUbCategory(@RequestBody MainCategoryDto mainCategoryDto) {

        List<String> subCategory = categoryService.findSubCategory(mainCategoryDto.getName());

        return subCategory;
    }

    @GetMapping("/setting/brand")
    public String addBrand(Model model) {
        model.addAttribute(new AddBrandDto());

        return "setting/setting-brand";
    }

    @PostMapping("/setting/brand")
    public String brandFile(@Valid AddBrandDto file, Errors errors) throws IOException {

        if(errors.hasErrors()) {
            return "setting/setting-brand";
        }

        brandService.saveBrand(file);

        return "redirect:/setting/brand";
    }

    @GetMapping("/setting/update/product/{itemId}")
    public String updateProductForm(@PathVariable("itemId") Long itemId, Model model) {
        Optional<Item> byId = itemRepository.findById(itemId);
        byId.ifPresent(item -> {
            model.addAttribute(item);
            model.addAttribute("goods", item.getGoods());
            List<ItemTag> itemTags = item.getItemTags();
            List<String> tagNames = new ArrayList<>();

            for (ItemTag itemTag : itemTags) {
                tagNames.add(itemTag.getTag().getTagName());
                System.out.println("ssssssssss = " + itemTag.getTag().getTagName());
            }
            model.addAttribute("tagNames",tagNames);

            model.addAttribute("categories", item.getItemCategories().get(0).getCategory());

        });

        List<Category> all = categoryRepository.findAll();

        List<Category> collect = all.stream().collect(Collectors.toList());

        List<String> brandNames = brandRepository.findAllBrandName();

        model.addAttribute(new ProductUpdateDto());

        if(collect != null) {
            model.addAttribute("collect", collect);
        }
        if(brandNames != null) {
            model.addAttribute("brandNames", brandNames);
        }

        model.addAttribute("sales", SaleStatus.values());
        model.addAttribute("seasons",Season.values());
        model.addAttribute("genders",Gender.values());

        return "setting/product-update";
    }

    @PostMapping("/setting/update/product/{itemId}")
    public String updateProduct(@PathVariable("itemId") Long itemId, @Valid ProductUpdateDto productUpdateDto,
                                Errors errors, Model model) throws IOException {
        if(errors.hasErrors()) {
            Optional<Item> byId = itemRepository.findById(itemId);
            byId.ifPresent(item -> {
                model.addAttribute(item);
                model.addAttribute("goods", item.getGoods());
                List<ItemTag> itemTags = item.getItemTags();
                List<String> tagNames = new ArrayList<>();

                for (ItemTag itemTag : itemTags) {
                    tagNames.add(itemTag.getTag().getTagName());
                    System.out.println("ssssssssss = " + itemTag.getTag().getTagName());
                }
                model.addAttribute("tagNames",tagNames);

                model.addAttribute("categories", item.getItemCategories().get(0).getCategory());

            });

            List<Category> all = categoryRepository.findAll();

            List<Category> collect = all.stream().collect(Collectors.toList());

            List<String> brandNames = brandRepository.findAllBrandName();

            if(collect != null) {
                model.addAttribute("collect", collect);
            }
            if(brandNames != null) {
                model.addAttribute("brandNames", brandNames);
            }

            model.addAttribute("sales", SaleStatus.values());
            model.addAttribute("seasons",Season.values());
            model.addAttribute("genders",Gender.values());
            return "setting/product-update";
        }

        System.out.println("aaaaaaaaaaaaa" + productUpdateDto);
        itemService.updateProduct(productUpdateDto, itemId);

        return "redirect:/setting";
    }

    @PostMapping("/option/delete")
    @ResponseBody
    public int deleteOption(@RequestBody DeleteOptionDto deleteOptionDto) {

        goodsService.deleteGoods(deleteOptionDto);

        return 1;
    }

    @GetMapping("/search-setting-main")
    public String searchSettingMainProcess (@Valid SettingMainCondition condition, @RequestParam(value = "page", defaultValue = "0") int page, Model model) {

        PageRequest of = PageRequest.of(page, 2);

        /*PageRequest of = PageRequest.of(0, 5);

        Page<Item> items = itemRepository.searchProductSetting(condition, of);
        System.out.println("fffffffffffff" + items.getContent());
        if(items!=null) {
            model.addAttribute("items",items.getContent());
            model.addAttribute("displays",DisplayStatus.values());
            model.addAttribute("saleStatus",SaleStatus.SALE);
        }*/

        Page<Item> content = itemRepository.searchProductSetting(condition, of);
        System.out.println("hhhhhhhhhhhhhh" + condition);
        List<ItemSettingMainDto> items = content.stream()
                .map(i -> new ItemSettingMainDto(i))
                .collect(Collectors.toList());

        if(items!=null) {
            model.addAttribute("pages", content);
            model.addAttribute("maxPage", 3);
            model.addAttribute("items2",items);
            model.addAttribute("displays",DisplayStatus.values());
            model.addAttribute("saleStatus",SaleStatus.SALE);
            if(condition.getItemName() != null) {
                model.addAttribute("condition", "itemName");
                model.addAttribute("query", condition.getItemName());
            }
            if(condition.getBrandName() != null) {
                model.addAttribute("condition", "brandName");
                model.addAttribute("query", condition.getBrandName());
            }





        }
        return "setting/setting-search-form";
    }
}

