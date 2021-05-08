package portfolioshop.orderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import portfolioshop.item.Item;
import portfolioshop.order.Order;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private int count;
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public OrderItem(Item item, String size, int count, Order order) {
        changeItem(item);
        this.size = size;
        this.count = count;
        changeOrder(order);
    }

    private void changeItem(Item item) {
        this.item = item;
        item.getOrderItems().add(this);
    }

    public void changeOrder(Order order) {
        this.order = order;
        order.getOrderItems().add(this);
    }
}
