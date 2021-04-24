package portfolioshop.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.item.Item;
import portfolioshop.itemTag.ItemTag;
import portfolioshop.itemTag.ItemTagRepository;
import portfolioshop.productSetting.dto.ProductAddDto;
import portfolioshop.tag.enumType.TagType;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final ItemTagRepository itemTagRepository;
    private final TagRepository tagRepository;

    public void addNewTag(String tagString, Item item) {
        String[] tags = tagString.split("#");
        if(tags.length != 0)  {
            for(int i = 1; i < tags.length; i++) {

                Tag tag = new Tag("#"+tags[i], TagType.HASH);

                ItemTag itemTag = new ItemTag();
                itemTag.changeItem(item);
                itemTag.changeTag(tag);

                itemTagRepository.save(itemTag);

                tagRepository.save(tag);

            }
        }
    }
}
