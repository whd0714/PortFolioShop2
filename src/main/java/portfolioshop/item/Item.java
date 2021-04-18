package portfolioshop.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.brand.Brand;
import portfolioshop.goods.Goods;
import portfolioshop.itemCategory.ItemCategory;
import portfolioshop.itemTag.ItemTag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemNo;

    private String itemName;

    private String itemPrice;

    private String season;

    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "item")
    private List<Goods> goods = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<ItemTag> itemTags = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<ItemCategory> itemCategories = new ArrayList<>();

}
