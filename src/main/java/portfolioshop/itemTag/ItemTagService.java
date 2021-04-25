package portfolioshop.itemTag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.category.Category;
import portfolioshop.item.Item;
import portfolioshop.item.ItemRepository;
import portfolioshop.productSetting.dto.ProductUpdateDto;
import portfolioshop.tag.Tag;
import portfolioshop.tag.TagRepository;
import portfolioshop.tag.TagService;
import portfolioshop.tag.enumType.TagType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemTagService {

    private final TagRepository tagRepository;
    private final ItemTagRepository itemTagRepository;
    private final TagService tagService;
    private final ItemRepository itemRepository;

    public void updateTag(ProductUpdateDto productUpdateDto, Item item) {

        List<ItemTag> itemTags = item.getItemTags();
        //Todo : ItemTag테이블에서 item과 연관관계없는 ItemTag, tag 지우기
        int idx = itemTags.size();
        for(int i = 0; i < idx; i++) {
            Tag tag = itemTags.get(0).getTag();
            Item itemByTag = itemTags.get(0).getItem();
            itemTags.get(0).removeAll(itemByTag, tag);
        }

        tagService.addNewTag(productUpdateDto.getItemTag(), item);

        /*List<ItemTag> itemTags2 = item.getItemTags();
        for(int i = 0; i < itemTags2.size(); i++) {
            if(itemTags2.get(i).getItem() == null) {
                item.removeTag(itemTags2.get(i));
            }
        }*/
        /*for (ItemTag itemTag : itemTags2) {
            System.out.println("ccccccccccccccccc22" + itemTag.getId());
            if(itemTag.getItem() == null) {
                item.removeTag(itemTag);
            }
        }*/

        /*List<ItemTag> allNull = itemTagRepository.findAllNull();

        for (ItemTag itemTag : allNull) {

            itemTagRepository.delete(itemTag);

        }*/

       /* for (ItemTag itemTag : itemTags) {
            Long tagId = itemTag.getTag().getId();
            Optional<Tag> byId = tagRepository.findById(tagId);
            byId.ifPresent(tag -> {
                itemTag.cutItem();
            });
            //itemTag.cutItem();

            itemTagRepository.delete(itemTag);
        }

        tagService.addNewTag(productUpdateDto.getItemTag(), item);*/

   /*     if(tags.length != 0)  {
            for(int i = 1; i < tags.length; i++) {
                Tag tag = new Tag("#"+tags[i], TagType.HASH);

                ItemTag itemTag = new ItemTag();
                itemTag.changeItem(item);
                itemTag.changeTag(tag);

                itemTagRepository.save(itemTag);

                tagRepository.save(tag);
            }
        }*/
    }
}
