package portfolioshop.goods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.goods.enumType.DisplayStatus;
import portfolioshop.goods.enumType.GoodsStatus;
import portfolioshop.goods.enumType.SaleStatus;
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

    private String size;

    private int count;

    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void changeItem(Item item) {
        this.item = item;
        item.getGoods().add(this);
    }

    public Goods(String size, int count, SaleStatus saleStatus) {
        this.size = size;
        this.count = count;
        this.saleStatus = saleStatus;
    }

    public void changeSize(String size) {
        this.size = size;
    }

    public void changeCount(Integer count) {
        this.count = count;
    }

    public void changeSaleStatuses(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }


}
