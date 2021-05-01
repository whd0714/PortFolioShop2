package portfolioshop.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.item.Item;
import portfolioshop.item.ItemRepository;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.member.CurrentUser;
import portfolioshop.member.Member;
import portfolioshop.productSetting.dto.CategoryDelDto;
import portfolioshop.productSetting.dto.CategoryNameDto;
import portfolioshop.productSetting.dto.MainCategoryDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String home(@CurrentUser Member member, Model model) {
        if(member != null) {
            model.addAttribute(member);
        }

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

        model.addAttribute("mainCategories", mainCategory);
        model.addAttribute("subCategories", subCategory);
        //List<Item> newItemByCategory = itemRepository.findNewItemByCategory(condition);

       // model.addAttribute("newItemByCategory", newItemByCategory);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "/member/login-form";
    }
}
