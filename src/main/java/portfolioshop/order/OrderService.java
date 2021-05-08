package portfolioshop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.cart.Cart;
import portfolioshop.cart.CartGoods;
import portfolioshop.cart.CartGoodsRepository;
import portfolioshop.goods.Goods;
import portfolioshop.goods.GoodsRepository;
import portfolioshop.item.Item;
import portfolioshop.item.ItemRepository;
import portfolioshop.member.Member;
import portfolioshop.member.MemberRepository;
import portfolioshop.order.dto.OptionValueDto;
import portfolioshop.orderItem.OrderItem;
import portfolioshop.orderItem.OrderItemRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final GoodsRepository goodsRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartGoodsRepository cartGoodsRepository;

    public void orderProcess(Long memberId, OptionValueDto optionValueDto) {
        Long[] itemId = optionValueDto.getItemId();
        String[] size = optionValueDto.getSize();
        int[] count = optionValueDto.getCount();
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0 ; i < itemId.length; i++) {
            Optional<Item> byId = itemRepository.findById(itemId[i]);
            byId.ifPresent(item -> {
                item.updateSaleRate();
                items.add(item);
            });
        }

        Order order = new Order();
        order.orderAt();
        orderRepository.save(order);

        Optional<Member> byId = memberRepository.findById(memberId);
        byId.ifPresent(member -> {
            order.changeMember(member);
            for(int i = 0; i < items.size(); i++) {
                OrderItem orderItem = new OrderItem(items.get(i), size[i], count[i], order);
                orderItemRepository.save(orderItem);
                Cart cart = member.getCart();
                Goods goods = goodsRepository.findGoodsByItemIdAndSize(items.get(i).getId(), size[i]);
                goods.upDateCount(count[i]);
                CartGoods cartGoods = cartGoodsRepository.findByCartGoodsByCartIdAndGoodsId(cart.getId(), goods.getId());
                cartGoodsRepository.delete(cartGoods);
            }
        });



    }
}
