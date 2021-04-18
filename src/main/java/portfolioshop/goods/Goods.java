package portfolioshop.goods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.goods.enumType.GoodsStatus;
import portfolioshop.item.Item;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    @Id @GeneratedValue
    @Column(name = "goods_id")
    private Long id;

    private int size;

    private int count;

    @Enumerated(EnumType.STRING)
    private GoodsStatus goodsStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
