package portfolioshop;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.brand.Brand;
import portfolioshop.brand.BrandRepository;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.goods.Goods;
import portfolioshop.goods.GoodsRepository;
import portfolioshop.goods.enumType.SaleStatus;
import portfolioshop.item.Item;
import portfolioshop.item.ItemRepository;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;
import portfolioshop.itemCategory.ItemCategory;
import portfolioshop.itemCategory.ItemCategoryRepository;
import portfolioshop.member.Member;
import portfolioshop.member.MemberRepository;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() throws IOException {
        initService.db1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final CategoryRepository categoryRepository;
        private final BrandRepository brandRepository;
        private final MemberRepository memberRepository;
        private final PasswordEncoder passwordEncoder;
        private final ItemCategoryRepository itemCategoryRepository;
        private final ItemRepository itemRepository;
        private final GoodsRepository goodsRepository;
        public void db1() throws IOException {

            String password = passwordEncoder.encode("11111111");

            Member member1 = new Member("aaa", password, "최종현");
            Member member2 = new Member("bbb", password, "이찬복");
            Member member3 = new Member("ccc", password, "이준택");

            memberRepository.save(member1);
            memberRepository.save(member2);
            memberRepository.save(member3);

            Category category = new Category("상의");
            Category category1 = new Category("하의");
            Category category2 = new Category("아우터");
            Category category3 = new Category("반팔");
            Category category4 = new Category("반바지");
            Category category5 = new Category("패딩");
            Category category6 = new Category("긴팔 티셔츠");
            Category category7 = new Category("나시");
            Category category8 = new Category("니트");
            Category category9 = new Category("맨투맨");
            Category category10 = new Category("후드");
            category3.changeCategory(category);
            category4.changeCategory(category1);
            category5.changeCategory(category2);
            category6.changeCategory(category);
            category7.changeCategory(category);
            category8.changeCategory(category);
            category9.changeCategory(category);
            category10.changeCategory(category);



            categoryRepository.save(category);
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);
            categoryRepository.save(category4);
            categoryRepository.save(category5);
            categoryRepository.save(category6);
            categoryRepository.save(category7);
            categoryRepository.save(category8);
            categoryRepository.save(category9);
            categoryRepository.save(category10);
            //category3.changeCategory(category1);

            BufferedImage shortT1 = ImageIO.read(new File("src/main/resources/static/image/short/1.jpg"));
            ByteArrayOutputStream baosShort1 = new ByteArrayOutputStream();
            ImageIO.write(shortT1, "jpg", baosShort1);
            byte[] bytesShort = baosShort1.toByteArray();
            byte[] encodeShort = Base64.getEncoder().encode(bytesShort);
            String imageShort = new String(encodeShort, "UTF-8");



            Item item = new Item(" CO2100ST02WH", "아치 로고 티셔츠 화이트", "Arch logo T-shirt white", 39000, Season.SUMMER, Gender.MAN, "", "아치형 로고 디자인",
                    imageShort);
            itemRepository.save(item);

            ItemCategory itemCategory = new ItemCategory();
            itemCategory.changeItem(item);
            itemCategory.changeCategory(category1);
            itemCategory.changeCategory(category3);
            itemCategoryRepository.save(itemCategory);

            Goods goods = new Goods("M", 10, SaleStatus.SALE);
            goods.changeItem(item);
            goodsRepository.save(goods);


            BufferedImage MBanner = ImageIO.read(new File("src/main/resources/static/image/banner/banner0.png"));
            BufferedImage LBanner = ImageIO.read(new File("src/main/resources/static/image/banner/banner1.png"));
            BufferedImage CBanner = ImageIO.read(new File("src/main/resources/static/image/banner/banner2.png"));


            ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();

            ImageIO.write(MBanner, "png", baos0);
            ImageIO.write(LBanner, "png", baos1);
            ImageIO.write(CBanner, "png", baos2);

            byte[] bytes0 = baos0.toByteArray();
            byte[] bytes1 = baos1.toByteArray();
            byte[] bytes2 = baos2.toByteArray();
            byte[] encode0 = Base64.getEncoder().encode(bytes0);
            byte[] encode1 = Base64.getEncoder().encode(bytes1);
            byte[] encode2 = Base64.getEncoder().encode(bytes2);

            String image0 = new String(encode0, "UTF-8");
            String image1 = new String(encode1, "UTF-8");
            String image2 = new String(encode2, "UTF-8");
            Brand brand0 = new Brand("무신사", "MUSINSA STANDARD", image0);
            Brand brand1 = new Brand("엘엠씨", "LMC", image1);
            Brand brand2 = new Brand("커버낫", "COVERNAT", image2);
            brandRepository.save(brand0);
            brandRepository.save(brand1);
            brandRepository.save(brand2);

            item.changeBrand(brand2);

        }
    }
}
