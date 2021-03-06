package portfolioshop.itemCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.brand.Brand;
import portfolioshop.category.Category;
import portfolioshop.item.Item;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategory {

    @Id @GeneratedValue
    @Column(name = "item_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void changeItem(Item item) {
        this.item = item;
        item.getItemCategories().add(this);
    }

    public void changeCategory(Category category) {
        this.category = category;
        category.getItemCategories().add(this);
    }


}
