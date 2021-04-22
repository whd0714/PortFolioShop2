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
import java.util.Base64;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    public void saveBrand(AddBrandDto file) throws IOException {


        byte[] bytes = file.getBrandBanner().getBytes();
        byte[] encode = Base64.getEncoder().encode(bytes);
        String img = new String(encode, "UTF-8");

        Brand brand = new Brand(file.getBrandName(), file.getBrandNameEng(), img);
        brandRepository.save(brand);
    }
}
