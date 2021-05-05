package portfolioshop.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.goods.Goods;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartGoods {

    @Id @GeneratedValue
    @Column(name = "cart_goods_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    private int count;

    public CartGoods(int count) {
        this.count = count;
    }

    public CartGoods(Cart cart, Goods goods, int count) {
        changeShoppingBasket(cart);
        changeGoods(goods);
        this.count = count;
    }

    public void changeShoppingBasket(Cart cart) {
        this.cart = cart;
        cart.getCartGoods().add(this);
    }

    public void changeGoods(Goods goods) {
        this.goods = goods;
        goods.getCartGoods().add(this);
    }

    public void changeCount(int count) {
        this.count = count;
    }
}
