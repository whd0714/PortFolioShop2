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
import portfolioshop.tag.TagService;

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
        private final TagService tagService;
        public void db1() throws IOException {

            /*--------------------------------회원----------------------------------------*/
            String password = passwordEncoder.encode("11111111");

            Member member1 = new Member("aaa", password, "최종현");
            Member member2 = new Member("bbb", password, "이찬복");
            Member member3 = new Member("ccc", password, "이준택");

            memberRepository.save(member1);
            memberRepository.save(member2);
            memberRepository.save(member3);

            /*--------------------------------카테고리----------------------------------------*/
            Category category = new Category("상의");
            Category category1 = new Category("바지");
            Category category2 = new Category("아우터");
            Category category3 = new Category("원피스");
            Category category4 = new Category("가방");
            Category category5 = new Category("신발");
            Category category6 = new Category("모자");
            Category category7 = new Category("반팔 티셔츠");
            Category category8 = new Category("긴팔 티셔츠");
            Category category9 = new Category("셔츠/블라우스");
            Category category10 = new Category("맨투맨/스웨트 셔츠");
            Category category11 = new Category("니트/스웨터");

            category7.changeCategory(category);
            category8.changeCategory(category);
            category9.changeCategory(category);
            category10.changeCategory(category);
            category11.changeCategory(category);

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
            categoryRepository.save(category11);

            /*--------------------------------상품----------------------------------------*/
            BufferedImage shortT1 = ImageIO.read(new File("src/main/resources/static/image/short/1.jpg"));
            ByteArrayOutputStream baosShort1 = new ByteArrayOutputStream();
            ImageIO.write(shortT1, "jpg", baosShort1);
            byte[] bytesShort = baosShort1.toByteArray();
            byte[] encodeShort = Base64.getEncoder().encode(bytesShort);
            String imageShort = new String(encodeShort, "UTF-8");

            /*--------------------------------반팔 티셔츠----------------------------------------*/
            Item item = new Item(" CO2100ST02WH", "아치 로고 티셔츠 화이트", "Arch logo T-shirt white", 39000, Season.SUMMER, Gender.WOMAN, "아치형 로고 디자인", "",
                    imageShort);
            itemRepository.save(item);

            Item item2 = new Item(" M99JGTT02WH", "릴렉스 핏 크루 넥 반팔 티셔츠 [화이트]", "Relaxed Fit Crew Neck Short Sleeve T-Shirt [White]", 19000, Season.SUMMER, Gender.MAN, "", "깔끔한 디자인",
                    imageShort);
            itemRepository.save(item2);

            Item item3 = new Item(" MIJON83T02WH", "베이식 크루 넥 반팔 티셔츠 [블랙]", "BASIC CREW NECK HALF SLEEVE T-SHIRT [BLACK]", 10900, Season.SUMMER, Gender.MAN, "깔끔한 디자인", "",
                    imageShort);
            itemRepository.save(item3);

            Item item4 = new Item(" C1A1011T02WH", "어센틱 로고 티셔츠 블랙", "Authentic logo T-shirt black", 39000, Season.SUMMER, Gender.MAN, "기본로고를 깔끔하게 표현", "",
                    imageShort);
            itemRepository.save(item4);

            Item item5 = new Item(" 21SS_NFH1_WH", "LMC ZT V2 티 화이트", "LMC ZT V2 TEE white", 42000, Season.SUMMER, Gender.WOMAN, "화려한 프린팅에 깔끔한 로고", "",
                    imageShort);
            itemRepository.save(item5);

            Item item6 = new Item(" 21SS_FM23_WH", "LMC WATER SPLASH TEE 화이트", "LMC WATER SPLASH TEE white", 39000, Season.SUMMER, Gender.MAN, "기본로고에 디자인을 추가", "",
                    imageShort);
            itemRepository.save(item6);

            ItemCategory itemCategory = new ItemCategory();
            itemCategory.changeItem(item);
            itemCategory.changeCategory(category7);
            itemCategoryRepository.save(itemCategory);

            ItemCategory itemCategory2 = new ItemCategory();
            itemCategory2.changeItem(item2);
            itemCategory2.changeCategory(category7);
            itemCategoryRepository.save(itemCategory2);

            ItemCategory itemCategory3 = new ItemCategory();
            itemCategory3.changeItem(item3);
            itemCategory3.changeCategory(category7);
            itemCategoryRepository.save(itemCategory3);

            ItemCategory itemCategory4 = new ItemCategory();
            itemCategory4.changeItem(item4);
            itemCategory4.changeCategory(category7);
            itemCategoryRepository.save(itemCategory4);

            ItemCategory itemCategory5 = new ItemCategory();
            itemCategory4.changeItem(item5);
            itemCategory4.changeCategory(category7);
            itemCategoryRepository.save(itemCategory5);

            ItemCategory itemCategory6 = new ItemCategory();
            itemCategory4.changeItem(item6);
            itemCategory4.changeCategory(category7);
            itemCategoryRepository.save(itemCategory6);

            /*--------------------------------반팔 티셔츠 옵션----------------------------------------*/
            Goods goods1 = new Goods("S", 20, SaleStatus.SALE);
            goods1.changeItem(item);
            goodsRepository.save(goods1);

            Goods goods1_2 = new Goods("M", 20, SaleStatus.SALE);
            goods1_2.changeItem(item);
            goodsRepository.save(goods1_2);

            Goods goods1_3 = new Goods("L", 20, SaleStatus.SALE);
            goods1_3.changeItem(item);
            goodsRepository.save(goods1_3);

            Goods goods2 = new Goods("S", 20, SaleStatus.SALE);
            goods2.changeItem(item2);
            goodsRepository.save(goods2);

            Goods goods2_2 = new Goods("M", 20, SaleStatus.SALE);
            goods2_2.changeItem(item2);
            goodsRepository.save(goods2_2);

            Goods goods2_3 = new Goods("L", 20, SaleStatus.SALE);
            goods2_3.changeItem(item2);
            goodsRepository.save(goods2_3);

            Goods goods3 = new Goods("S", 20, SaleStatus.SALE);
            goods3.changeItem(item3);
            goodsRepository.save(goods3);

            Goods goods3_2 = new Goods("M", 20, SaleStatus.SALE);
            goods3_2.changeItem(item3);
            goodsRepository.save(goods3_2);

            Goods goods3_3 = new Goods("L", 20, SaleStatus.SALE);
            goods3_3.changeItem(item3);
            goodsRepository.save(goods3_3);

            Goods goods4 = new Goods("S", 0, SaleStatus.SALE);
            goods4.changeItem(item4);
            goodsRepository.save(goods4);

            Goods goods4_2 = new Goods("M", 20, SaleStatus.SALE);
            goods4_2.changeItem(item4);
            goodsRepository.save(goods4_2);

            Goods goods4_3 = new Goods("L", 20, SaleStatus.SALE);
            goods4_3.changeItem(item4);
            goodsRepository.save(goods4_3);

            Goods goods5 = new Goods("S", 0, SaleStatus.NOSALE);
            goods5.changeItem(item5);
            goodsRepository.save(goods5);

            Goods goods5_2 = new Goods("M", 20, SaleStatus.SALE);
            goods5_2.changeItem(item5);
            goodsRepository.save(goods5_2);

            Goods goods5_3 = new Goods("L", 20, SaleStatus.SALE);
            goods5_3.changeItem(item5);
            goodsRepository.save(goods5_3);

            Goods goods6 = new Goods("S", 5, SaleStatus.SALE);
            goods6.changeItem(item5);
            goodsRepository.save(goods6);

            Goods goods6_2 = new Goods("M", 20, SaleStatus.SALE);
            goods6_2.changeItem(item5);
            goodsRepository.save(goods6_2);

            Goods goods6_3 = new Goods("L", 0, SaleStatus.SALE);
            goods6_3.changeItem(item5);
            goodsRepository.save(goods6_3);

            /*--------------------------------태그----------------------------------------*/
            String tag1 = "#로고#로고티#반팔티#오버핏#티셔츠";
            tagService.addNewTag(tag1, item);

            String tag2 = "#기본티#라운드넥#루즈핏#면티셔츠#무지티#반팔티#홈웨어컬렉션#베이식";
            tagService.addNewTag(tag2, item2);

            String tag3 = "#기본티#라운드넥#루즈핏#면티셔츠#무지티#반팔티#홈웨어컬렉션";
            tagService.addNewTag(tag3, item3);

            String tag4 = "#로고#반팔#반팔티#오버핏#반팔티셔츠#베이직티셔츠";
            tagService.addNewTag(tag4, item3);

            String tag5 = "#반팔티#반팔#그래픽#그래픽티셔츠";
            tagService.addNewTag(tag5, item5);

            String tag6 = "#반팔티셔츠#오버핏반팔#로고";
            tagService.addNewTag(tag6, item6);

            /*--------------------------------브랜드----------------------------------------*/
            BufferedImage Banner1 = ImageIO.read(new File("src/main/resources/static/image/banner/banner0.png"));
            BufferedImage Banner2 = ImageIO.read(new File("src/main/resources/static/image/banner/banner1.png"));
            BufferedImage Banner3 = ImageIO.read(new File("src/main/resources/static/image/banner/banner2.png"));
            BufferedImage Banner4 = ImageIO.read(new File("src/main/resources/static/image/banner/banner3.png"));
            BufferedImage Banner5 = ImageIO.read(new File("src/main/resources/static/image/banner/banner4.png"));

            ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
            ByteArrayOutputStream baos4 = new ByteArrayOutputStream();

            ImageIO.write(Banner1, "png", baos0);
            ImageIO.write(Banner2, "png", baos1);
            ImageIO.write(Banner3, "png", baos2);
            ImageIO.write(Banner4, "png", baos3);
            ImageIO.write(Banner5, "png", baos4);

            byte[] bytes0 = baos0.toByteArray();
            byte[] bytes1 = baos1.toByteArray();
            byte[] bytes2 = baos2.toByteArray();
            byte[] bytes3 = baos3.toByteArray();
            byte[] bytes4 = baos4.toByteArray();
            byte[] encode0 = Base64.getEncoder().encode(bytes0);
            byte[] encode1 = Base64.getEncoder().encode(bytes1);
            byte[] encode2 = Base64.getEncoder().encode(bytes2);
            byte[] encode3 = Base64.getEncoder().encode(bytes3);
            byte[] encode4 = Base64.getEncoder().encode(bytes4);

            String image0 = new String(encode0, "UTF-8");
            String image1 = new String(encode1, "UTF-8");
            String image2 = new String(encode2, "UTF-8");
            String image3 = new String(encode3, "UTF-8");
            String image4 = new String(encode4, "UTF-8");

            Brand brand0 = new Brand("무신사", "MUSINSA STANDARD", image0);
            Brand brand1 = new Brand("엘엠씨", "LMC", image1);
            Brand brand2 = new Brand("커버낫", "COVERNAT", image2);
            Brand brand3 = new Brand("페이탈리즘", "FATALISM", image3);
            Brand brand4 = new Brand("나이키", "NIKE", image4);
            brandRepository.save(brand0);
            brandRepository.save(brand1);
            brandRepository.save(brand2);
            brandRepository.save(brand3);
            brandRepository.save(brand4);

            item.changeBrand(brand2);
            item2.changeBrand(brand0);
            item3.changeBrand(brand0);
            item4.changeBrand(brand2);
            item5.changeBrand(brand1);
            item6.changeBrand(brand1);

        }
    }
}
