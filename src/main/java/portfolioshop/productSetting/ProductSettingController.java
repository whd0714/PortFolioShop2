package portfolioshop.productSetting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolioshop.brand.BrandRepository;
import portfolioshop.brand.BrandService;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.category.CategoryService;
import portfolioshop.item.ItemService;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;
import portfolioshop.productSetting.dto.*;

import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProductSettingController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final BrandService brandService;
    private final BrandRepository brandRepository;

    @GetMapping("/setting")
    public String settingForm() {
        return "setting/setting-form";
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
    public String addProduct(@Valid ProductAddDto productAddDto) throws IOException {

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
    @PostMapping("/setting/category")
    public String addCategory22(@Valid CategoryNameDto categoryNameDto) {
        System.out.println(categoryNameDto);
        System.out.println("productAddDto");

        return "redirect:/setting/category";
    }

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
    public String brandFile(@Valid AddBrandDto file) throws IOException {

        brandService.saveBrand(file);

        return "redirect:/setting/brand";
    }
}

