package portfolioshop.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.item.Item;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public void confirm(String brandName, Item item) {
        Brand brand = brandRepository.findByBrandName(brandName);
        if(brand == null) {
            Brand brand1 = new Brand(brandName);
            item.changeBrand(brand1);
            brandRepository.save(brand1);
        } else {
            item.changeBrand(brand);
            brandRepository.save(brand);
        }

    }

}
