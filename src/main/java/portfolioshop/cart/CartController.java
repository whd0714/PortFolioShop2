package portfolioshop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import portfolioshop.cart.dto.CartGoodsIdDto;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.main.dto.MainSearchDto;
import portfolioshop.member.CurrentUser;
import portfolioshop.member.Member;
import portfolioshop.member.MemberRepository;
import portfolioshop.order.dto.OptionValueDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/add/cart")
    @ResponseBody
    public int addItem(@CurrentUser Member member, @RequestBody OptionValueDto optionValueDto) {
        Cart cart = member.getCart();
        if (cart == null) {
            cart = cartService.createCart(member);
        }

        cartService.processBasket(optionValueDto, cart);

        return 1;
    }

    @GetMapping("/cart")
    public String cartForm(@CurrentUser Member member, Model model) {
        if(member != null) {
            Optional<Member> byId = memberRepository.findById(member.getId());
            byId.ifPresent(m -> {
                //Cart cart = cartRepository.findCartByMemberId(m.getId());
                Cart cart = m.getCart();
                if(cart!= null) {
                    model.addAttribute("cart", cart.getCartGoods());
                    model.addAttribute("cartSize", m.getCart().getCartGoods().size());
                } else {
                    model.addAttribute("cartSize", 0);
                }
                model.addAttribute(m);
            });
        }

        model.addAttribute("mainSearch", new MainSearchDto());
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
        model.addAttribute("optionDto",new OptionValueDto());

        return "member/cart-form";
    }

    @PostMapping("/del/cartGoods")
    @ResponseBody
    public int delCartGoods(@RequestBody CartGoodsIdDto cartGoodsIdDto) {

        cartService.delCartGoods(cartGoodsIdDto.getCartGoodsId());

        return 1;
    }


}
