package portfolioshop.itemTag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.item.Item;
import portfolioshop.tag.Tag;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemTag {

    @Id @GeneratedValue
    @Column(name = "item_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void changeItem(Item item) {
        this.item = item;
        item.getItemTags().add(this);
    }

    public void changeTag(Tag tag) {
        this.tag = tag;
        tag.getItemTags().add(this);
    }

    public void removeAll(Item item, Tag tag) {
        this.item = null;
        this.tag = null;
        item.removeTag(this);
        tag.removeItem(this);
    }

  /*  public void cutItem(Item item, Tag tag) {
        this.item = null;
        this.tag = null;
        item.removeTag(this);
        tag.removeItem(this);

    }*/

/*    public void removeAll() {
        this.item = null;
        this.tag = null;
    }

    public void removeItem() {
        this.item = null;
    }

    public void removeTag() {
        this.tag = null;
    }*/
}
