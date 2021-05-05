package portfolioshop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.goods.Goods;
import portfolioshop.goods.GoodsRepository;
import portfolioshop.member.Member;
import portfolioshop.order.dto.OptionValueDto;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final GoodsRepository goodsRepository;
    private final CartGoodsRepository cartGoodsRepository;

    public Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.changeMember(member);
        cartRepository.save(cart);

        return cart;
    }

    public void processBasket(OptionValueDto optionValueDto, Cart cart) {
        Long[] itemId = optionValueDto.getItemId();
        String[] size = optionValueDto.getSize();
        int[] count = optionValueDto.getCount();

        Optional<Cart> byId = cartRepository.findById(cart.getId());

        byId.ifPresent(c -> {
            for(int i = 0; i < size.length; i++) {
                Goods goods = goodsRepository.findGoodsByItemIdAndSize(itemId[i], size[i]);
                CartGoods cartGoods = cartGoodsRepository.findByCartGoodsByCartIdAndGoodsId(c.getId(), goods.getId());
                if(cartGoods != null) {
                    cartGoods.changeCount(count[i]);
                } else {
                    CartGoods sb = new CartGoods(c, goods, count[i]);
                    cartGoodsRepository.save(sb);
                }
            }

        });
    }

    public void delCartGoods(Long cartGoodsId) {
        Optional<CartGoods> byId = cartGoodsRepository.findById(cartGoodsId);
        byId.ifPresent(cartGoods -> {
            cartGoodsRepository.delete(cartGoods);
        });
    }

    /*for (int i = 0; i < itemId.length; i++) {
        */

       /* List<ShoppingBasketItem> shoppingBasketItems = shoppingBasket.getShoppingBasketItems();
        System.out.println("1111111111" + shoppingBasketItems);
        if(shoppingBasketItems != null) {
            System.out.println("???????????");
            for (ShoppingBasketItem shoppingBasketItem : shoppingBasketItems) {

                for (int i = 0; i < itemId.length; i++) {
                    Goods goods = goodsRepository.findGoodsByItemIdAndSize(itemId[i], size[i]);
                    if (shoppingBasketItem.getGoods() == goods) {
                        shoppingBasketItem.getGoods();
                        shoppingBasketItem.changeCount(count[i]);
                    } else {
                        ShoppingBasketItem sb = new ShoppingBasketItem(shoppingBasket, goods, count[i]);
                        shoppingBasketItemRepository.save(sb);
                    }
                }

            }
        } else {
            System.out.println("!!!!!!!!!!!!!");
            for (int i = 0; i < itemId.length; i++) {
                Goods goods = goodsRepository.findGoodsByItemIdAndSize(itemId[i], size[i]);
                ShoppingBasketItem sb = new ShoppingBasketItem(shoppingBasket, goods, count[i]);
                shoppingBasketItemRepository.save(sb);
            }
        }*/

}
