package portfolioshop.productSetting.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import portfolioshop.item.ItemRepository;
import portfolioshop.productSetting.dto.ProductAddDto;

@Component
@RequiredArgsConstructor
public class ItemAddValidator implements Validator {

    private final ItemRepository itemRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductAddDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductAddDto productAddDto = (ProductAddDto) o;
        if(itemRepository.existsByItemNo(productAddDto.getItemNo())) {
            errors.rejectValue("itemNo", "error.itemNo", null, "이미 존재하는 품명입니다.");
        }
        if(itemRepository.existsByItemName(productAddDto.getItemName())) {
            errors.rejectValue("itemName", "error.itemName", null, "이미 존재하는 상품명입니다.");
        }
        if(itemRepository.existsByItemNameEng(productAddDto.getItemNameEng())) {
            errors.rejectValue("itemNameEng", "error.itemNameEng", null, "이미 존재하는 상품명(영어)입니다.");
        }
    }
}
