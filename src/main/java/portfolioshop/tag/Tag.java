package portfolioshop.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.itemTag.ItemTag;
import portfolioshop.tag.enumType.TagType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String tagName;

    @Enumerated(EnumType.STRING)
    private TagType tagType;

    @OneToMany(mappedBy = "tag",  orphanRemoval = true)
    private List<ItemTag> itemTags = new ArrayList<>();

    public Tag(String tagName, TagType tagType) {
        this.tagName = tagName;
        this.tagType = tagType;
    }

    public void removeItem(ItemTag itemTag) {
        this.getItemTags().remove(itemTag);
    }
}
