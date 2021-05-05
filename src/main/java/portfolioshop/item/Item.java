package portfolioshop.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.brand.Brand;
import portfolioshop.goods.Goods;
import portfolioshop.goods.enumType.DisplayStatus;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;
import portfolioshop.itemCategory.ItemCategory;
import portfolioshop.itemTag.ItemTag;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private String itemNameEng;

    private int itemPrice;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String description;

    @Lob
    private String subDescription;

    @Lob
    private String itemImage;

    @Enumerated(EnumType.STRING)
    private DisplayStatus displayStatus;

    private LocalDateTime createItemTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "item")
    private List<Goods> goods = new ArrayList<>();

    @OneToMany(mappedBy = "item", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ItemTag> itemTags = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemCategory> itemCategories = new ArrayList<>();



    public void changeBrand(Brand brand) {
        this.brand = brand;
        brand.getItems().add(this);
    }


    public Item(String itemNo, String itemName, String itemNameEng, int itemPrice, Season season, Gender gender,
                String subDescription, String description, String itemImage) {
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.itemNameEng = itemNameEng;
        this.itemPrice = itemPrice;
        this.season = season;
        this.gender = gender;
        this.subDescription = subDescription;
        this.description = description;
        this.itemImage = itemImage;
        this.displayStatus = DisplayStatus.진열함;
        this.createItemTime = LocalDateTime.now();
    }

    public void changeImg(String img) {
        this.itemImage = img;
    }

    public void removeTag(ItemTag itemTag) {
        this.getItemTags().remove(itemTag);
    }

    public void updateItem(String itemNo, String itemName, String itemNameEng, int itemPrice, Season season, Gender gender, String subDescription, String description) {
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.itemNameEng = itemNameEng;
        this.itemPrice = itemPrice;
        this.season = season;
        this.gender = gender;
        this.subDescription = subDescription;
        this.description = description;
    }

    public void changeDisplay(DisplayStatus displayStatus) {
        this.displayStatus = displayStatus;
    }


}
