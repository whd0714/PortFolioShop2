package portfolioshop.itemTag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.category.Category;
import portfolioshop.item.Item;
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

    public void updateTag(ProductUpdateDto productUpdateDto, Item item) {

        List<ItemTag> itemTags = item.getItemTags();

        for (ItemTag itemTag : itemTags) {
            itemTag.cutItem();
            itemTagRepository.delete(itemTag);
        }

        tagService.addNewTag(productUpdateDto.getItemTag(), item);

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
