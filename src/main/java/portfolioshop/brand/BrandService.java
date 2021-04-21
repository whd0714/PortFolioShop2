package portfolioshop.brand;

import lombok.RequiredArgsConstructor;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import portfolioshop.item.Item;
import portfolioshop.productSetting.dto.AddBrandDto;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    public void saveBrand(AddBrandDto file) throws IOException {
       // Brand brand = new Brand(file.getBrandName(), file.getBrandBanner());
        String fileName = file.getBrandBanner().getOriginalFilename();

        byte[] bytes = file.getBrandBanner().getBytes();
        Brand brand = new Brand(file.getBrandName(), bytes);
        brandRepository.save(brand);
    }
}
