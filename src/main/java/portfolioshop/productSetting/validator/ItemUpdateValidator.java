package portfolioshop.productSetting.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import portfolioshop.item.ItemRepository;
import portfolioshop.productSetting.dto.ProductAddDto;
import portfolioshop.productSetting.dto.ProductUpdateDto;

@Component
@RequiredArgsConstructor
public class ItemUpdateValidator implements Validator {

    private final ItemRepository itemRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductUpdateDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductUpdateDto productUpdateDto = (ProductUpdateDto) o;

    }
}
