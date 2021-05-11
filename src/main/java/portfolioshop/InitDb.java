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
            Category category12 = new Category("데님 팬츠");
            Category category13 = new Category("코튼 팬츠");
            Category category14 = new Category("슈트 팬츠/슬랙스");
            Category category15 = new Category("숏 팬츠");
            Category category16 = new Category("슈트/블레이저 재킷");
            Category category17 = new Category("카디건");
            Category category18 = new Category("겨울 싱글 코트");
            Category category19 = new Category("패딩");
            Category category20 = new Category("미니 원피스");
            Category category21 = new Category("미디 원피스");
            Category category22 = new Category("맥시 원피스");
            Category category23 = new Category("백팩");
            Category category24 = new Category("메신저/크로스백");


            category7.changeCategory(category);
            category8.changeCategory(category);
            category9.changeCategory(category);
            category10.changeCategory(category);
            category11.changeCategory(category);

            category12.changeCategory(category1);
            category13.changeCategory(category1);
            category14.changeCategory(category1);
            category15.changeCategory(category1);

            category16.changeCategory(category2);
            category17.changeCategory(category2);
            category18.changeCategory(category2);
            category19.changeCategory(category2);

            category20.changeCategory(category3);
            category21.changeCategory(category3);
            category22.changeCategory(category3);

            category23.changeCategory(category4);
            category24.changeCategory(category4);

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
            categoryRepository.save(category12);
            categoryRepository.save(category13);
            categoryRepository.save(category14);
            categoryRepository.save(category15);
            categoryRepository.save(category16);
            categoryRepository.save(category17);
            categoryRepository.save(category18);
            categoryRepository.save(category19);
            categoryRepository.save(category20);
            categoryRepository.save(category21);
            categoryRepository.save(category22);
            categoryRepository.save(category23);
            categoryRepository.save(category24);

            /*--------------------------------상품 이미지----------------------------------------*/
            BufferedImage shortT1 = ImageIO.read(new File("src/main/resources/static/image/short/1.jpg"));
            ByteArrayOutputStream baosShort1 = new ByteArrayOutputStream();
            ImageIO.write(shortT1, "jpg", baosShort1);
            byte[] bytesShort = baosShort1.toByteArray();
            byte[] encodeShort = Base64.getEncoder().encode(bytesShort);
            String imageShort = new String(encodeShort, "UTF-8");

            /*--------------------------------상품 생성----------------------------------------*/
            /*상품생성 반팔*/
            Item item = new Item("O2100ST02WH", "아치 로고 티셔츠 화이트", "Arch logo T-shirt white", 39000, Season.SUMMER, Gender.WOMAN, "아치형 로고 디자인", "",
                    imageShort);
            itemRepository.save(item);

            Item item2 = new Item("M99JGTT02WH", "릴렉스 핏 크루 넥 반팔 티셔츠 [화이트]", "Relaxed Fit Crew Neck Short Sleeve T-Shirt [White]", 19000, Season.SUMMER, Gender.MAN, "", "깔끔한 디자인",
                    imageShort);
            itemRepository.save(item2);

            Item item3 = new Item("MIJON83T02WH", "베이식 크루 넥 반팔 티셔츠 [블랙]", "BASIC CREW NECK HALF SLEEVE T-SHIRT [BLACK]", 10900, Season.SUMMER, Gender.MAN, "깔끔한 디자인", "",
                    imageShort);
            itemRepository.save(item3);

            Item item4 = new Item("C1A1011T02WH", "어센틱 로고 티셔츠 블랙", "Authentic logo T-shirt black", 39000, Season.SUMMER, Gender.MAN, "기본로고를 깔끔하게 표현", "",
                    imageShort);
            itemRepository.save(item4);

            Item item5 = new Item("21SS_NFH1_WH", "LMC ZT V2 티 화이트", "LMC ZT V2 TEE white", 42000, Season.SUMMER, Gender.WOMAN, "화려한 프린팅에 깔끔한 로고", "",
                    imageShort);
            itemRepository.save(item5);

            Item item6 = new Item("21SS_FM23_WH", "LMC WATER SPLASH TEE 화이트", "LMC WATER SPLASH TEE white", 39000, Season.SUMMER, Gender.MAN, "기본로고에 디자인을 추가", "",
                    imageShort);
            itemRepository.save(item6);

            Item item7 = new Item("KSHUT021", "반팔티 소매 KS [블랙]", "LOCATION SHORT SLEEVE KS [BLACK]", 39000, Season.SUMMER, Gender.MAN, "심플한 로고티셔츠", "",
                    imageShort);
            itemRepository.save(item7);

            Item item8 = new Item("KSHUE045", "스토크 오버핏 반팔 티셔츠 BLACK", "Stoke Overfit Short Sleeve T-Shirt BLACK", 39000, Season.SUMMER, Gender.MAN, "편안하고 신축성있고 편안한 착용감", "",
                    imageShort);
            itemRepository.save(item8);

            Item item9 = new Item("KSHUT021", "퓨라 반팔티 AR5IO4-010", "Fura Short Sleeve Tea AR5IO4-010", 51000, Season.SUMMER, Gender.WOMAN, "심플한 로고티셔츠", "",
                    imageShort);
            itemRepository.save(item9);

            Item item10 = new Item("KSHUE045", "반팔티 ARRNEI-101", "Short Sleeve Tee ARRNEI-101", 56000, Season.SUMMER, Gender.MAN, "깔끔한 디자인과 부드러운 소재", "",
                    imageShort);
            itemRepository.save(item10);

            /*상품생성 긴팔 티셔츠*/
            Item item11 = new Item("MISS11209-WH", "베식 긴팔 티셔츠 [화이트]", "Besik Long Sleeve T-Shirt [White]", 13500, Season.SPRING, Gender.MAN, "여유 있는 롱 슬리브 티셔츠", "촘촘하게 짜여진 면 100%을 사용해 편안한 착용감을 제공한다. 적당한 폭과 깊이의 원단을 바디 원단과 밸런스를 맞춰 사용했다.",
                    imageShort);
            itemRepository.save(item11);

            Item item12 = new Item("MISS11209-BK", "베식 긴팔 티셔츠 [블랙]", "Besik Long Sleeve T-Shirt [Black]", 13500, Season.SPRING, Gender.MAN, "여유 있는 롱 슬리브 티셔츠", "촘촘하게 짜여진 면 100%을 사용해 편안한 착용감을 제공한다. 적당한 폭과 깊이의 원단을 바디 원단과 밸런스를 맞춰 사용했다.",
                    imageShort);
            itemRepository.save(item12);

            Item item13 = new Item("MJJF0LT09BK", "스트라이프 롱 슬리브 블랙", "STRIPE LONG SLEEVE BLACK", 45000, Season.AUTUMN, Gender.WOMAN, "개성적인 스프라이트 티셔츠", "",
                    imageShort);
            itemRepository.save(item13);

            Item item14 = new Item("MN5S0001WH", "하이 레이어드 티셔츠", "HIGH LAYERED T-SHIRT", 39000, Season.AUTUMN, Gender.MAN, "실크의 부드러운 촉감과 고급스러운 광택을 느낄 수 있음", "",
                    imageShort);
            itemRepository.save(item14);

            Item item15 = new Item("R98920932", "포인트 서클 롱티셔츠", "POINT CIRCLE LONG T-SHIRT", 34000, Season.AUTUMN, Gender.WOMAN, "특수양면을 사용해 부드러운 촉감을 느낄수 있다", "",
                    imageShort);
            itemRepository.save(item15);

            Item item16 = new Item("AAM81233", "롱슬리브 헤비 오버핏 스크레치 로고R", "Long Sleeve Heavy Overfit Scratch Logo R", 49100, Season.AUTUMN, Gender.MAN, "이중 넥라인으로 퀄리티를 강화", "",
                    imageShort);
            itemRepository.save(item16);

            Item item17 = new Item("DDG2157A29", "스트라이프 코메 로고 롱슬리브 크림", "Stripe Comet Logo Long Sleeve Cream", 47000, Season.SPRING, Gender.WOMAN, "특수 재질로 세탁 후 원단 줄어듬을 방지", "",
                    imageShort);
            itemRepository.save(item17);

            Item item18 = new Item("DDG22GGA29", "스트라이프 와펜 로고 롱슬리브 네이비", "Striped Wapen Logo Long Sleeve Navy", 46000, Season.AUTUMN, Gender.WOMAN, "비침이 적은 원단을 사용", "",
                    imageShort);
            itemRepository.save(item18);

            Item item19 = new Item("FFE00145BK", "레이어드 어센틱 롱슬리브 블랙", "Layered Authentic Long Sleeve Black", 49000, Season.SPRING, Gender.MAN, "이중넥에 이중소매로 두툼한 소재", "",
                    imageShort);
            itemRepository.save(item19);

            Item item20 = new Item("FF0035EBK", "베츠 스트라이프 롱슬리브 블랙", "Betz Stripe Long Sleeve Black", 45000, Season.AUTUMN, Gender.MAN, "캐주얼 스트라이프", "",
                    imageShort);
            itemRepository.save(item20);

            /*상품생성 셔츠/블라우스*/
            Item item21 = new Item("AA0HF9001", "솔리드 옥스포드 오버셔츠(스카이블루)", "Solid Oxford Overshirt (Sky Blue)", 67000, Season.SPRING, Gender.MAN, "자연스러운 핏을 제공", "",
                    imageShort);
            itemRepository.save(item21);

            Item item22 = new Item("AA0HFNJ71", "루즈핏 오픈카라 텐셀 린넨 셔츠 [네이비]", "LOOSE-FIT OPEN COLLAR TENCEL LINEN SHIRTS [NAVY]", 60000, Season.SUMMER, Gender.MAN, "린넨과 천연면을 혼합하여 피부에 자극이 적고 뛰어난 보냉력으로 여름에 착용하기 적합", "",
                    imageShort);
            itemRepository.save(item22);

            Item item23 = new Item("BB0HFDG341", "울탄 체크 셔츠 (크림)", "Wooltan Check Shirts (Cream)", 33000, Season.SPRING, Gender.MAN, "두툼하고 부드러운 원단", "",
                    imageShort);
            itemRepository.save(item23);

            Item item24 = new Item("BB0HBNG258", "플러프 무드 체크 셔츠 네이비", "Fluff mood check shirt navy", 64000, Season.AUTUMN, Gender.MAN, "감각적인 체크무늬", "",
                    imageShort);
            itemRepository.save(item24);

            Item item25 = new Item("CC0CC0233", "솔리드 옥스포드 오버셔츠(화이트)", "Solid Oxford Overshirt (White)", 65000, Season.SPRING, Gender.MAN, "옥스포드원단에 깔끔한 디자인", "",
                    imageShort);
            itemRepository.save(item25);

            Item item26 = new Item("T20SJ001441", "루즈핏 오픈카라 텐셀 린넨 셔츠 [오트밀]", "LOOSE-FIT OPEN COLLAR TENCEL LINEN SHIRTS [OATMEAL]", 55000, Season.SUMMER, Gender.MAN, "린넨재질로 여름에 입기 적합", "",
                    imageShort);
            itemRepository.save(item26);

            Item item27 = new Item("DD0CCFF33", "빈티지 블랙 도트 스트라이프 셔츠 S22", "Layla endless love Vintage Black dot Stripe shirt S22", 65000, Season.SPRING, Gender.MAN, "베이지컬러에 스프라이트 포인트셔츠", "",
                    imageShort);
            itemRepository.save(item27);

            Item item28 = new Item("DD0CCF4453", "릴렉스 핏 옥스포드 셔츠 [화이트]", "RELAX FIT OXFORD SHIRT [WHITE]", 24500, Season.SPRING, Gender.MAN, "입기 편안한 재질과 핏한 셔츠", "",
                    imageShort);
            itemRepository.save(item28);

            Item item29 = new Item("FF0CCFF33", "로프 스트라이프 셔츠 S46 스카이 블루", "Layla blind for love Rope Stripe shirt S46 Sky Blue", 65000, Season.SPRING, Gender.MAN, "하늘색 스프라이트 포인트셔츠", "",
                    imageShort);
            itemRepository.save(item29);

            Item item30 = new Item("FF0CCF4453", "프렌치 스트라이프 셔츠", "French striped shirt", 56000, Season.SPRING, Gender.MAN, "차분한 회색에 내추럴한 룩을 연출하기 좋음", "",
                    imageShort);
            itemRepository.save(item30);

            /*상품생성 맨투맨/스웨트셔츠*/
            Item item31 = new Item("AAPSCRB801M", "NYC 위치 스웨트 셔츠", "NYC LOCATION SWEAT SHIRT", 55000, Season.SPRING, Gender.MAN, "부드러운 면100%에 안정감있는 두께를 느낄수 있음", "",
                    imageShort);
            itemRepository.save(item31);

            Item item32 = new Item("AAPS15B801M", "AAR 맨투맨 그레이", "AAR Sweatshirt Gray", 56000, Season.SPRING, Gender.MAN, "코튼100%에 차분한 그레이 컬러", "",
                    imageShort);
            itemRepository.save(item32);

            Item item33 = new Item("BB12PGGRB801M", "스웨트 셔츠 [블랙]", "SWEAT SHIRT [BLACK]", 21900, Season.SPRING, Gender.MAN, "여유 있는 레귤러 핏의 스웨트셔츠", "촘촘하게 짜여진 면 100% 프렌치 테리 원단을 사용하여 편안한 활동성을 제공한다.",
                    imageShort);
            itemRepository.save(item33);

            Item item34 = new Item("BB12PGGRBFF13", "UNIVERSITY 맨투맨 네이비", "UNIVERSITY SWEAT NAVY", 67000, Season.AUTUMN, Gender.MAN, "로고와 프린트가 적절히 들어감", "",
                    imageShort);
            itemRepository.save(item34);

            Item item35 = new Item("CC12PGGRBKKKM", "클럽 맨투맨 블랙", "Club Sweatshirt Black", 86000, Season.SPRING, Gender.MAN, "기본 로고티", "",
                    imageShort);
            itemRepository.save(item35);

            Item item36 = new Item("CC12PGGRBKKK2", "1999 맨투맨 네이비", "1999 sweatshirts navy", 67000, Season.AUTUMN, Gender.MAN, "심플한 디자인", "",
                    imageShort);
            itemRepository.save(item36);

            Item item37 = new Item("DD12PGGR334M", "바시티 피그먼트 스웨트셔츠 블루", "VARSITY PIGMENT SWEATSHIRT BLUE", 53000, Season.SPRING, Gender.MAN, "이중 넥으로 두께와 편안함을 두루 갖춤", "",
                    imageShort);
            itemRepository.save(item37);

            Item item38 = new Item("DD12PGGR334A", "쿠퍼 로고 맨투맨 네이비", "Cooper logo sweatshirt navy", 69000, Season.AUTUMN, Gender.WOMAN, "심플한 로고 디자인", "",
                    imageShort);
            itemRepository.save(item38);

            Item item39 = new Item("FF12PTGR34M", "F 로고 맨투맨 그레이", "F logo sweatshirt gray", 54000, Season.SPRING, Gender.MAN, "깔끔한 F로고 맨투맨", "",
                    imageShort);
            itemRepository.save(item39);

            Item item40 = new Item("FF12PTGR34K", "스몰 로고 풀기모 맨투맨 티셔츠", "Small logo full-brushed sweatshirt", 75000, Season.AUTUMN, Gender.MAN, "심플한 로고 디자인", "",
                    imageShort);
            itemRepository.save(item40);

            /*상품생성 니트/스웨터*/
            Item item41 = new Item("AAbhasnnri_1", "하프 폴라 니트 티셔츠", "Half Pola Knit T-Shirt", 77000, Season.AUTUMN, Gender.MAN, "부드러운 촉감에 신축성이 좋아 신체사이즈에 구애 받지 않음", "",
                    imageShort);
            itemRepository.save(item41);

            Item item42 = new Item("AAbhasnnri_2", "목폴라 스웨터 브라운", "NECK RIB KNIT SWEATER_BROWN", 61000, Season.AUTUMN, Gender.MAN, "부드러운 촉감에 실을 가득채워 밀도높은 니트 스웨터", "",
                    imageShort);
            itemRepository.save(item42);

            Item item43 = new Item("BBbh2sngg3", "화란 세미오버 니트 블랙", "Hwaran Semiover Knit Black", 77900, Season.AUTUMN, Gender.MAN, "천연 가공으로 더욱 부드러운 터치감", "",
                    imageShort);
            itemRepository.save(item43);

            Item item44 = new Item("BBbh2sggg2", "크루넥 오버니트(차콜)", "Crew Neck Over Knit (Charcoal)", 52000, Season.AUTUMN, Gender.MAN, "오버핏으로 여유있게 착용 가능", "",
                    imageShort);
            itemRepository.save(item44);

            Item item45 = new Item("CCbh2sngg43", "소프트코튼 드랍숄더 오버핏 와플 니트 BLACK", "Soft Cotton Drop Shoulder Overfit Waffle Knit BLACK", 55000, Season.SPRING, Gender.MAN, "기본 감성에 충실한 니트", "",
                    imageShort);
            itemRepository.save(item45);

            Item item46 = new Item("Cbh2sggg42", "브러쉬 아가일 니트 BLACK", "Brush Argyle Knit BLACK", 54000, Season.SPRING, Gender.MAN, "기본 감성에 충실한 니트", "",
                    imageShort);
            itemRepository.save(item46);

            Item item47 = new Item("DDh2s1n2gg43", "미니멀 크루 넥 니트 [블랙]", "MINIMAL CREW NECK KNIT [BLACK]", 55900, Season.SPRING, Gender.MAN, "여유 있는 실루엣의 미니멀 크루 넥 니트.", "미니멀한 디자인과 여유 있는 세미 오버 핏으로 편안하면서도 자연스러운 실루엣을 연출할 수 있다.",
                    imageShort);
            itemRepository.save(item47);

            Item item48 = new Item("DD2sg1g2g42", "부클 니트 롱 슬리브 블랙", "BOUCLE KNIT LONG SLEEVE BLACK", 54000, Season.AUTUMN, Gender.MAN, "독특한 패턴의 니트", "",
                    imageShort);
            itemRepository.save(item48);

            Item item49 = new Item("FFh2s1n2gg43", "소프트 베이직 터틀넥 니트 블랙", "SOFT BASIC TURTLENECK KNIT BLACK", 30900, Season.SPRING, Gender.MAN, "트렌디한 디자인의 니트", "",
                    imageShort);
            itemRepository.save(item49);

            Item item50 = new Item("FF2sg1g2g42", "울 배색 라운드 니트_Open Blue", "Wool Matching Round Knit_Open Blue", 55000, Season.AUTUMN, Gender.MAN, "부드러운 색감의 니트", "",
                    imageShort);
            itemRepository.save(item50);

            /*상품생성 데님 팬츠*/
            Item item51 = new Item("AAS-WDDP022", "와이드 데님 팬츠 (LIGHT BLUE)", "WIDE DENIM PANTS (LIGHT BLUE)", 46000, Season.AUTUMN, Gender.MAN, "와이드한 핏감과 탄탄한 데님소재", "",
                    imageShort);
            itemRepository.save(item51);

            Item item52 = new Item("AAS-WDDP023", "데님 [크롭 스트레이트]", "OBJECT JEANS [CROP STRAIGHT]", 77000, Season.SPRING, Gender.MAN, "면100% 네츄럴 옷감", "",
                    imageShort);
            itemRepository.save(item52);

            Item item53 = new Item("BBS-WDDP022", "와이드 데님 팬츠 (DEEP GREY)", "와이드 데님 팬츠 (DEEP GREY)", 47900, Season.AUTUMN, Gender.MAN, "총장이 넙고 자연스럽게 떨어지는 핏", "",
                    imageShort);
            itemRepository.save(item53);

            Item item54 = new Item("BBS-WDDP022", "펑크 타운-MOD", "Punk Town - MOD crop", 92000, Season.AUTUMN, Gender.MAN, "딱 떨어지는 자연스러운 핏", "",
                    imageShort);
            itemRepository.save(item54);

            Item item55 = new Item("CCS-WDDP022", "적당한 인디고 스트레이트 핏", "Moderation indigo straight fit", 88000, Season.SPRING, Gender.MAN, "적당한 핏", "",
                    imageShort);
            itemRepository.save(item55);

            Item item56 = new Item("CCS-WDDP022", "슬림 크롭 데님 팬츠 [라이트 인디고]", "SLIM CROP DENIM PANTS [LIGHT INDIGO]", 33000, Season.SPRING, Gender.MAN, "편안하면서도 슬림한 핏의 크롭 데님 팬츠.", "허벅지부터 발목까지 자연스럽게 좁아지는 슬림 핏에 발목까지 내려오는 크롭 기장으로 트렌디하게 착용할 수 있다.",
                    imageShort);
            itemRepository.save(item56);

            Item item57 = new Item("DDS-WDDP022", "와이드 스트레이트", "JET BLACK JEANS [WIDE STRAIGHT]", 77000, Season.SPRING, Gender.MAN, "면 100% 와이드 데님 팬츠", "",
                    imageShort);
            itemRepository.save(item57);

            Item item58 = new Item("DDS-WDDP022", "크롭 테이퍼드 핏 블랙", "BLACK NORMAL CHIP", 84000, Season.AUTUMN, Gender.MAN, "촘촘한 원단으로 내구성을 높임", "",
                    imageShort);
            itemRepository.save(item58);

            Item item59 = new Item("FFS-WDDP022", "페인트 진", "trabus paint jeans", 99600, Season.SPRING, Gender.MAN, "너무 밝지도 어둡지도 않은 적당한 컬러", "",
                    imageShort);
            itemRepository.save(item59);

            Item item60 = new Item("FFS-WDDP022", "스탠다드 진 블랙", "BLACK STANDARD3 JEANS", 77000, Season.AUTUMN, Gender.MAN, "두께감은 있지만 신축성이 좋음", "",
                    imageShort);
            itemRepository.save(item60);

            /*상품생성 코튼 팬츠*/
            Item item61 = new Item("AA1905667", "롱 와이드 코튼팬츠(베이지)", "Long Wide Cotton Pants (Beige)", 66000, Season.AUTUMN, Gender.WOMAN, "와이드한 핏감의 코튼 팬츠", "",
                    imageShort);
            itemRepository.save(item61);

            Item item62 = new Item("BB1905667", "린넨 9부 밴딩 팬츠", "Linen Banding Pants", 40000, Season.SUMMER, Gender.MAN, "여름에 맞는 코튼 팬츠", "",
                    imageShort);
            itemRepository.save(item62);

            Item item63 = new Item("CC1905667", "테이퍼드 치노 팬츠 [블랙]", "TAPERED CHINO PANTS [BLACK]", 37900, Season.AUTUMN, Gender.MAN, "스탠다드 코튼 팬츠", "",
                    imageShort);
            itemRepository.save(item63);

            Item item64 = new Item("DD1905667", "워싱 테이퍼드핏 면팬츠", "washing TaperedFit pants", 52000, Season.AUTUMN, Gender.MAN, "미니멀한 핏", "",
                    imageShort);
            itemRepository.save(item64);

            Item item65 = new Item("FF1905667", "크라우 와이드 카고 벌룬팬츠 카키", "크라우 와이드 카고 벌룬팬츠 카키", 31500, Season.SPRING, Gender.MAN, "클래식한 디자인과 핏", "",
                    imageShort);
            itemRepository.save(item65);

            /*상품생성 슈트 팬츠/슬랙스*/
            Item item66 = new Item("AAMIPS0001FI", "테이퍼드 히든 밴딩 크롭 슬랙스 [블랙]", "TAPERED HIDDEN BANDING CROP SLACKS [BLACK]", 32000, Season.AUTUMN, Gender.WOMAN, "편안하면서도 슬림한 테이퍼드 핏의 히든 밴딩 크롭 슬랙스.", "신축성이 뛰어나며 덕분에 자유로운 활동성을 보장한다. 적당한 두께와 중량으로 한여름을 제외한 사계절 내내 활용할 수 있다.",
                    imageShort);
            itemRepository.save(item66);

            Item item67 = new Item("BBIPS0001FI", "밴딩 와이드 슬랙스", "wide banding Slacks", 39000, Season.SUMMER, Gender.MAN, "미니멀한 와이드 슬랙스", "",
                    imageShort);
            itemRepository.save(item67);

            Item item68 = new Item("CCIPS0001FI", "세미 와이드 밴딩 슬랙스", "semi wide banding slacks", 50000, Season.SUMMER, Gender.MAN, "여름에 적당한 슬랙스", "",
                    imageShort);
            itemRepository.save(item68);

            Item item69 = new Item("DDIPS0001FI", "와이드 밴딩 슬랙스_블랙", "Wide Banding Slacks_Black", 33500, Season.AUTUMN, Gender.MAN, "신축성이 뛰어난 와이드 슬랙스", "",
                    imageShort);
            itemRepository.save(item69);

            Item item70 = new Item("FFIPS0001FI", "크롭 테이퍼드 슬랙스 [블랙]", "CROP TAPERED SLACKS [BLACK]", 29900, Season.SPRING, Gender.MAN, "자연스러운 핏감", "",
                    imageShort);
            itemRepository.save(item70);

            /*상품생성 숏 팬츠*/
            Item item71 = new Item("AAB55655-AB0", "로고 반바지 블랙", "Logo shorts black", 43000, Season.SUMMER, Gender.MAN, "편안한 기본 숏 팬츠", "",
                    imageShort);
            itemRepository.save(item71);

            Item item72 = new Item("BBB55655-AB0", "유틸리티 쇼츠 [블랙]", "UTILITY SHORTS [BLACK]", 21000, Season.SUMMER, Gender.MAN, "활용성이 높은 숏팬츠", "",
                    imageShort);
            itemRepository.save(item72);

            Item item73 = new Item("CCB55655-AB0", "팩 숏 팬츠 - 블랙", "FAC SHORT BLACK", 30000, Season.SUMMER, Gender.MAN, "시원한 느낌의 원단", "",
                    imageShort);
            itemRepository.save(item73);

            Item item74 = new Item("DDB55655-AB0", "유틸리티 숏 팬츠 - 블랙", "UTILITY SHORT BLACK", 22500, Season.SUMMER, Gender.MAN, "피트니스 라이프스타일", "",
                    imageShort);
            itemRepository.save(item74);

            Item item75 = new Item("FFB55655-AB0", "메탈 카고 쇼츠_라이트그레이", "METAL CARGO SHORTS_L.Grey", 29900, Season.SUMMER, Gender.MAN, "한여름에도 쾌적한 착용감", "",
                    imageShort);
            itemRepository.save(item75);

            /*상품생성 슈트/블레이저 재킷*/
            Item item76 = new Item("AAMIJKKKJ2-02", "베이식 블레이저 [블랙]", "BASIC BLAZER [BLACK]", 70000, Season.AUTUMN, Gender.MAN, "베이식한 디자인의 블레이저.", "신축성이 뛰어나며 덕분에 자유로운 활동성을 보장한다.",
                    imageShort);
            itemRepository.save(item76);

            Item item77 = new Item("BBMIJKKKJ2-02", "글렌체크 싱글 블레이져", "Glencheck single blazer", 130000, Season.SPRING, Gender.MAN, "모던하고 세련된 디자인", "",
                    imageShort);
            itemRepository.save(item77);

            Item item78 = new Item("CCMIJKKKJ2-02", "세미오버핏 싱글 블레이저 자켓", "Semi-over fit single blazer jacket", 159000, Season.SPRING, Gender.MAN, "세미오버핏 블레이저 자켓", "",
                    imageShort);
            itemRepository.save(item78);

            Item item79 = new Item("DDMIJKKKJ2-02", "오버사이즈 울 블레이져", "OVERSIZED WOOL BLAZER", 171000, Season.AUTUMN, Gender.MAN, "고급스러운 소재로 부드럽고 은은한 광택", "",
                    imageShort);
            itemRepository.save(item79);

            Item item80 = new Item("FFMIJKKKJ2-02", "루즈핏 멀티 체크 블레이저", "LOOSE FIT MULTI CHECK BLAZER", 110000, Season.AUTUMN, Gender.MAN, "고급스러운 패턴", "",
                    imageShort);
            itemRepository.save(item80);

            /*상품카테고리 반팔 티셔츠*/
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
            itemCategory5.changeItem(item5);
            itemCategory5.changeCategory(category7);
            itemCategoryRepository.save(itemCategory5);

            ItemCategory itemCategory6 = new ItemCategory();
            itemCategory6.changeItem(item6);
            itemCategory6.changeCategory(category7);
            itemCategoryRepository.save(itemCategory6);

            ItemCategory itemCategory7 = new ItemCategory();
            itemCategory7.changeItem(item7);
            itemCategory7.changeCategory(category7);
            itemCategoryRepository.save(itemCategory7);

            ItemCategory itemCategory8 = new ItemCategory();
            itemCategory8.changeItem(item8);
            itemCategory8.changeCategory(category7);
            itemCategoryRepository.save(itemCategory8);

            ItemCategory itemCategory9 = new ItemCategory();
            itemCategory9.changeItem(item9);
            itemCategory9.changeCategory(category7);
            itemCategoryRepository.save(itemCategory9);

            ItemCategory itemCategory10 = new ItemCategory();
            itemCategory10.changeItem(item10);
            itemCategory10.changeCategory(category7);
            itemCategoryRepository.save(itemCategory10);

            /*상품카테고리 긴팔 티셔츠*/
            ItemCategory itemCategory11 = new ItemCategory();
            itemCategory11.changeItem(item11);
            itemCategory11.changeCategory(category8);
            itemCategoryRepository.save(itemCategory11);

            ItemCategory itemCategory12 = new ItemCategory();
            itemCategory12.changeItem(item12);
            itemCategory12.changeCategory(category8);
            itemCategoryRepository.save(itemCategory12);

            ItemCategory itemCategory13 = new ItemCategory();
            itemCategory13.changeItem(item13);
            itemCategory13.changeCategory(category8);
            itemCategoryRepository.save(itemCategory13);

            ItemCategory itemCategory14 = new ItemCategory();
            itemCategory14.changeItem(item14);
            itemCategory14.changeCategory(category8);
            itemCategoryRepository.save(itemCategory14);

            ItemCategory itemCategory15 = new ItemCategory();
            itemCategory15.changeItem(item15);
            itemCategory15.changeCategory(category8);
            itemCategoryRepository.save(itemCategory15);

            ItemCategory itemCategory16 = new ItemCategory();
            itemCategory16.changeItem(item16);
            itemCategory16.changeCategory(category8);
            itemCategoryRepository.save(itemCategory16);

            ItemCategory itemCategory17 = new ItemCategory();
            itemCategory17.changeItem(item17);
            itemCategory17.changeCategory(category8);
            itemCategoryRepository.save(itemCategory17);

            ItemCategory itemCategory18 = new ItemCategory();
            itemCategory18.changeItem(item18);
            itemCategory18.changeCategory(category8);
            itemCategoryRepository.save(itemCategory18);

            ItemCategory itemCategory19 = new ItemCategory();
            itemCategory19.changeItem(item19);
            itemCategory19.changeCategory(category8);
            itemCategoryRepository.save(itemCategory19);

            ItemCategory itemCategory20 = new ItemCategory();
            itemCategory20.changeItem(item20);
            itemCategory20.changeCategory(category8);
            itemCategoryRepository.save(itemCategory20);

            /*상품카테고리 셔츠/블라우스*/
            ItemCategory itemCategory21 = new ItemCategory();
            itemCategory21.changeItem(item21);
            itemCategory21.changeCategory(category9);
            itemCategoryRepository.save(itemCategory21);

            ItemCategory itemCategory22 = new ItemCategory();
            itemCategory22.changeItem(item22);
            itemCategory22.changeCategory(category9);
            itemCategoryRepository.save(itemCategory22);

            ItemCategory itemCategory23 = new ItemCategory();
            itemCategory23.changeItem(item23);
            itemCategory23.changeCategory(category9);
            itemCategoryRepository.save(itemCategory23);

            ItemCategory itemCategory24 = new ItemCategory();
            itemCategory24.changeItem(item24);
            itemCategory24.changeCategory(category9);
            itemCategoryRepository.save(itemCategory24);

            ItemCategory itemCategory25 = new ItemCategory();
            itemCategory25.changeItem(item25);
            itemCategory25.changeCategory(category9);
            itemCategoryRepository.save(itemCategory25);

            ItemCategory itemCategory26 = new ItemCategory();
            itemCategory26.changeItem(item26);
            itemCategory26.changeCategory(category9);
            itemCategoryRepository.save(itemCategory26);

            ItemCategory itemCategory27 = new ItemCategory();
            itemCategory27.changeItem(item27);
            itemCategory27.changeCategory(category9);
            itemCategoryRepository.save(itemCategory27);

            ItemCategory itemCategory28 = new ItemCategory();
            itemCategory28.changeItem(item28);
            itemCategory28.changeCategory(category9);
            itemCategoryRepository.save(itemCategory28);

            ItemCategory itemCategory29 = new ItemCategory();
            itemCategory29.changeItem(item29);
            itemCategory29.changeCategory(category9);
            itemCategoryRepository.save(itemCategory29);

            ItemCategory itemCategory30 = new ItemCategory();
            itemCategory30.changeItem(item30);
            itemCategory30.changeCategory(category9);
            itemCategoryRepository.save(itemCategory30);

            /*상품카테고리 맨투맨/스웨트셔츠*/
            ItemCategory itemCategory31 = new ItemCategory();
            itemCategory31.changeItem(item31);
            itemCategory31.changeCategory(category10);
            itemCategoryRepository.save(itemCategory31);

            ItemCategory itemCategory32 = new ItemCategory();
            itemCategory32.changeItem(item32);
            itemCategory32.changeCategory(category10);
            itemCategoryRepository.save(itemCategory32);

            ItemCategory itemCategory33 = new ItemCategory();
            itemCategory33.changeItem(item33);
            itemCategory33.changeCategory(category10);
            itemCategoryRepository.save(itemCategory33);

            ItemCategory itemCategory34 = new ItemCategory();
            itemCategory34.changeItem(item34);
            itemCategory34.changeCategory(category10);
            itemCategoryRepository.save(itemCategory34);

            ItemCategory itemCategory35 = new ItemCategory();
            itemCategory35.changeItem(item35);
            itemCategory35.changeCategory(category10);
            itemCategoryRepository.save(itemCategory35);

            ItemCategory itemCategory36 = new ItemCategory();
            itemCategory36.changeItem(item36);
            itemCategory36.changeCategory(category10);
            itemCategoryRepository.save(itemCategory36);

            ItemCategory itemCategory37 = new ItemCategory();
            itemCategory37.changeItem(item37);
            itemCategory37.changeCategory(category10);
            itemCategoryRepository.save(itemCategory37);

            ItemCategory itemCategory38 = new ItemCategory();
            itemCategory38.changeItem(item38);
            itemCategory38.changeCategory(category10);
            itemCategoryRepository.save(itemCategory38);

            ItemCategory itemCategory39 = new ItemCategory();
            itemCategory39.changeItem(item39);
            itemCategory39.changeCategory(category10);
            itemCategoryRepository.save(itemCategory39);

            ItemCategory itemCategory40 = new ItemCategory();
            itemCategory40.changeItem(item40);
            itemCategory40.changeCategory(category10);
            itemCategoryRepository.save(itemCategory40);

            /*상품카테고리 니트/스웨터*/
            ItemCategory itemCategory41 = new ItemCategory();
            itemCategory41.changeItem(item41);
            itemCategory41.changeCategory(category11);
            itemCategoryRepository.save(itemCategory41);

            ItemCategory itemCategory42 = new ItemCategory();
            itemCategory42.changeItem(item42);
            itemCategory42.changeCategory(category11);
            itemCategoryRepository.save(itemCategory42);

            ItemCategory itemCategory43 = new ItemCategory();
            itemCategory43.changeItem(item43);
            itemCategory43.changeCategory(category11);
            itemCategoryRepository.save(itemCategory43);

            ItemCategory itemCategory44 = new ItemCategory();
            itemCategory44.changeItem(item44);
            itemCategory44.changeCategory(category11);
            itemCategoryRepository.save(itemCategory44);

            ItemCategory itemCategory45 = new ItemCategory();
            itemCategory45.changeItem(item45);
            itemCategory45.changeCategory(category11);
            itemCategoryRepository.save(itemCategory45);

            ItemCategory itemCategory46 = new ItemCategory();
            itemCategory46.changeItem(item46);
            itemCategory46.changeCategory(category11);
            itemCategoryRepository.save(itemCategory46);

            ItemCategory itemCategory47 = new ItemCategory();
            itemCategory47.changeItem(item47);
            itemCategory47.changeCategory(category11);
            itemCategoryRepository.save(itemCategory47);

            ItemCategory itemCategory48 = new ItemCategory();
            itemCategory48.changeItem(item48);
            itemCategory48.changeCategory(category11);
            itemCategoryRepository.save(itemCategory48);

            ItemCategory itemCategory49 = new ItemCategory();
            itemCategory49.changeItem(item49);
            itemCategory49.changeCategory(category11);
            itemCategoryRepository.save(itemCategory49);

            ItemCategory itemCategory50 = new ItemCategory();
            itemCategory50.changeItem(item50);
            itemCategory50.changeCategory(category11);
            itemCategoryRepository.save(itemCategory50);

            /*상품카테고리 데님 팬츠*/
            ItemCategory itemCategory51 = new ItemCategory();
            itemCategory51.changeItem(item51);
            itemCategory51.changeCategory(category12);
            itemCategoryRepository.save(itemCategory51);

            ItemCategory itemCategory52 = new ItemCategory();
            itemCategory52.changeItem(item52);
            itemCategory52.changeCategory(category12);
            itemCategoryRepository.save(itemCategory52);

            ItemCategory itemCategory53 = new ItemCategory();
            itemCategory53.changeItem(item53);
            itemCategory53.changeCategory(category12);
            itemCategoryRepository.save(itemCategory53);

            ItemCategory itemCategory54 = new ItemCategory();
            itemCategory54.changeItem(item54);
            itemCategory54.changeCategory(category12);
            itemCategoryRepository.save(itemCategory54);

            ItemCategory itemCategory55 = new ItemCategory();
            itemCategory55.changeItem(item55);
            itemCategory55.changeCategory(category12);
            itemCategoryRepository.save(itemCategory55);

            ItemCategory itemCategory56 = new ItemCategory();
            itemCategory56.changeItem(item56);
            itemCategory56.changeCategory(category12);
            itemCategoryRepository.save(itemCategory56);

            ItemCategory itemCategory57 = new ItemCategory();
            itemCategory57.changeItem(item57);
            itemCategory57.changeCategory(category12);
            itemCategoryRepository.save(itemCategory57);

            ItemCategory itemCategory58 = new ItemCategory();
            itemCategory58.changeItem(item58);
            itemCategory58.changeCategory(category12);
            itemCategoryRepository.save(itemCategory58);

            ItemCategory itemCategory59 = new ItemCategory();
            itemCategory59.changeItem(item59);
            itemCategory59.changeCategory(category12);
            itemCategoryRepository.save(itemCategory59);

            ItemCategory itemCategory60 = new ItemCategory();
            itemCategory60.changeItem(item60);
            itemCategory60.changeCategory(category12);
            itemCategoryRepository.save(itemCategory60);

            /*상품카테고리 코튼 팬츠*/
            ItemCategory itemCategory61 = new ItemCategory();
            itemCategory61.changeItem(item61);
            itemCategory61.changeCategory(category13);
            itemCategoryRepository.save(itemCategory61);

            ItemCategory itemCategory62 = new ItemCategory();
            itemCategory62.changeItem(item62);
            itemCategory62.changeCategory(category13);
            itemCategoryRepository.save(itemCategory62);

            ItemCategory itemCategory63 = new ItemCategory();
            itemCategory63.changeItem(item63);
            itemCategory63.changeCategory(category13);
            itemCategoryRepository.save(itemCategory63);

            ItemCategory itemCategory64 = new ItemCategory();
            itemCategory64.changeItem(item64);
            itemCategory64.changeCategory(category13);
            itemCategoryRepository.save(itemCategory64);

            ItemCategory itemCategory65 = new ItemCategory();
            itemCategory65.changeItem(item65);
            itemCategory65.changeCategory(category13);
            itemCategoryRepository.save(itemCategory65);

            /*상품카테고리 슈트 팬츠/슬랙스*/
            ItemCategory itemCategory66 = new ItemCategory();
            itemCategory66.changeItem(item66);
            itemCategory66.changeCategory(category14);
            itemCategoryRepository.save(itemCategory66);

            ItemCategory itemCategory67 = new ItemCategory();
            itemCategory67.changeItem(item67);
            itemCategory67.changeCategory(category14);
            itemCategoryRepository.save(itemCategory67);

            ItemCategory itemCategory68 = new ItemCategory();
            itemCategory68.changeItem(item68);
            itemCategory68.changeCategory(category14);
            itemCategoryRepository.save(itemCategory68);

            ItemCategory itemCategory69 = new ItemCategory();
            itemCategory69.changeItem(item69);
            itemCategory69.changeCategory(category14);
            itemCategoryRepository.save(itemCategory69);

            ItemCategory itemCategory70 = new ItemCategory();
            itemCategory70.changeItem(item70);
            itemCategory70.changeCategory(category14);
            itemCategoryRepository.save(itemCategory70);

            /*상품카테고리 숏 팬츠*/
            ItemCategory itemCategory71 = new ItemCategory();
            itemCategory71.changeItem(item71);
            itemCategory71.changeCategory(category15);
            itemCategoryRepository.save(itemCategory71);

            ItemCategory itemCategory72 = new ItemCategory();
            itemCategory72.changeItem(item72);
            itemCategory72.changeCategory(category15);
            itemCategoryRepository.save(itemCategory72);

            ItemCategory itemCategory73 = new ItemCategory();
            itemCategory73.changeItem(item73);
            itemCategory73.changeCategory(category15);
            itemCategoryRepository.save(itemCategory73);

            ItemCategory itemCategory74 = new ItemCategory();
            itemCategory74.changeItem(item74);
            itemCategory74.changeCategory(category15);
            itemCategoryRepository.save(itemCategory74);

            ItemCategory itemCategory75 = new ItemCategory();
            itemCategory75.changeItem(item75);
            itemCategory75.changeCategory(category15);
            itemCategoryRepository.save(itemCategory75);

            /*상품카테고리 슈트/블레이저 재킷*/
            ItemCategory itemCategory76 = new ItemCategory();
            itemCategory76.changeItem(item76);
            itemCategory76.changeCategory(category16);
            itemCategoryRepository.save(itemCategory76);

            ItemCategory itemCategory77 = new ItemCategory();
            itemCategory77.changeItem(item77);
            itemCategory77.changeCategory(category16);
            itemCategoryRepository.save(itemCategory77);

            ItemCategory itemCategory78 = new ItemCategory();
            itemCategory78.changeItem(item78);
            itemCategory78.changeCategory(category16);
            itemCategoryRepository.save(itemCategory78);

            ItemCategory itemCategory79 = new ItemCategory();
            itemCategory79.changeItem(item79);
            itemCategory79.changeCategory(category16);
            itemCategoryRepository.save(itemCategory79);

            ItemCategory itemCategory80 = new ItemCategory();
            itemCategory80.changeItem(item80);
            itemCategory80.changeCategory(category16);
            itemCategoryRepository.save(itemCategory80);


            /*--------------------------------옵션----------------------------------------*/
            /*반팔 티셔츠 옵션*/
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
            goods6.changeItem(item6);
            goodsRepository.save(goods6);

            Goods goods6_2 = new Goods("M", 20, SaleStatus.SALE);
            goods6_2.changeItem(item6);
            goodsRepository.save(goods6_2);

            Goods goods6_3 = new Goods("L", 0, SaleStatus.SALE);
            goods6_3.changeItem(item6);
            goodsRepository.save(goods6_3);

            Goods goods7 = new Goods("S", 5, SaleStatus.SALE);
            goods7.changeItem(item7);
            goodsRepository.save(goods7);

            Goods goods7_2 = new Goods("M", 20, SaleStatus.SALE);
            goods7_2.changeItem(item7);
            goodsRepository.save(goods7_2);

            Goods goods7_3 = new Goods("L", 0, SaleStatus.SALE);
            goods7_3.changeItem(item7);
            goodsRepository.save(goods7_3);

            Goods goods8 = new Goods("S", 5, SaleStatus.SALE);
            goods8.changeItem(item8);
            goodsRepository.save(goods8);

            Goods goods8_2 = new Goods("M", 20, SaleStatus.SALE);
            goods8_2.changeItem(item8);
            goodsRepository.save(goods8_2);

            Goods goods8_3 = new Goods("L", 0, SaleStatus.SALE);
            goods8_3.changeItem(item8);
            goodsRepository.save(goods8_3);

            Goods goods9 = new Goods("S", 5, SaleStatus.SALE);
            goods9.changeItem(item9);
            goodsRepository.save(goods9);

            Goods goods9_2 = new Goods("M", 20, SaleStatus.SALE);
            goods9_2.changeItem(item9);
            goodsRepository.save(goods9_2);

            Goods goods9_3 = new Goods("L", 0, SaleStatus.SALE);
            goods9_3.changeItem(item9);
            goodsRepository.save(goods9_3);

            Goods goods10 = new Goods("S", 5, SaleStatus.SALE);
            goods10.changeItem(item10);
            goodsRepository.save(goods10);

            Goods goods10_2 = new Goods("M", 20, SaleStatus.SALE);
            goods10_2.changeItem(item10);
            goodsRepository.save(goods10_2);

            Goods goods10_3 = new Goods("L", 0, SaleStatus.SALE);
            goods10_3.changeItem(item10);
            goodsRepository.save(goods10_3);

            /*긴팔 티셔츠 옵션*/
            Goods goods11 = new Goods("S", 5, SaleStatus.SALE);
            goods11.changeItem(item11);
            goodsRepository.save(goods11);

            Goods goods11_2 = new Goods("M", 20, SaleStatus.SALE);
            goods11_2.changeItem(item11);
            goodsRepository.save(goods11_2);

            Goods goods11_3 = new Goods("L", 0, SaleStatus.SALE);
            goods11_3.changeItem(item11);
            goodsRepository.save(goods11_3);

            Goods goods12 = new Goods("S", 5, SaleStatus.SALE);
            goods12.changeItem(item12);
            goodsRepository.save(goods12);

            Goods goods12_2 = new Goods("M", 20, SaleStatus.SALE);
            goods12_2.changeItem(item12);
            goodsRepository.save(goods12_2);

            Goods goods12_3 = new Goods("L", 0, SaleStatus.SALE);
            goods12_3.changeItem(item12);
            goodsRepository.save(goods12_3);

            Goods goods13 = new Goods("S", 5, SaleStatus.SALE);
            goods13.changeItem(item13);
            goodsRepository.save(goods13);

            Goods goods13_2 = new Goods("M", 20, SaleStatus.SALE);
            goods13_2.changeItem(item13);
            goodsRepository.save(goods13_2);

            Goods goods13_3 = new Goods("L", 0, SaleStatus.SALE);
            goods13_3.changeItem(item13);
            goodsRepository.save(goods13_3);

            Goods goods14 = new Goods("S", 11, SaleStatus.SALE);
            goods14.changeItem(item14);
            goodsRepository.save(goods14);

            Goods goods14_2 = new Goods("M", 20, SaleStatus.SALE);
            goods14_2.changeItem(item14);
            goodsRepository.save(goods14_2);

            Goods goods14_3 = new Goods("L", 30, SaleStatus.SALE);
            goods14_3.changeItem(item14);
            goodsRepository.save(goods14_3);

            Goods goods15 = new Goods("S", 5, SaleStatus.SALE);
            goods15.changeItem(item15);
            goodsRepository.save(goods15);

            Goods goods15_2 = new Goods("M", 20, SaleStatus.SALE);
            goods15_2.changeItem(item15);
            goodsRepository.save(goods15_2);

            Goods goods15_3 = new Goods("L", 0, SaleStatus.SALE);
            goods15_3.changeItem(item15);
            goodsRepository.save(goods15_3);

            Goods goods16 = new Goods("S", 11, SaleStatus.SALE);
            goods16.changeItem(item16);
            goodsRepository.save(goods16);

            Goods goods16_2 = new Goods("M", 20, SaleStatus.SALE);
            goods16_2.changeItem(item16);
            goodsRepository.save(goods16_2);

            Goods goods16_3 = new Goods("L", 30, SaleStatus.SALE);
            goods16_3.changeItem(item16);
            goodsRepository.save(goods16_3);

            Goods goods17 = new Goods("S", 5, SaleStatus.SALE);
            goods17.changeItem(item17);
            goodsRepository.save(goods17);

            Goods goods17_2 = new Goods("M", 20, SaleStatus.SALE);
            goods17_2.changeItem(item17);
            goodsRepository.save(goods17_2);

            Goods goods17_3 = new Goods("L", 0, SaleStatus.SALE);
            goods17_3.changeItem(item17);
            goodsRepository.save(goods17_3);

            Goods goods18 = new Goods("S", 11, SaleStatus.SALE);
            goods18.changeItem(item18);
            goodsRepository.save(goods18);

            Goods goods18_2 = new Goods("M", 20, SaleStatus.SALE);
            goods18_2.changeItem(item18);
            goodsRepository.save(goods18_2);

            Goods goods18_3 = new Goods("L", 30, SaleStatus.SALE);
            goods18_3.changeItem(item18);
            goodsRepository.save(goods18_3);

            Goods goods19 = new Goods("S", 5, SaleStatus.SALE);
            goods19.changeItem(item19);
            goodsRepository.save(goods19);

            Goods goods19_2 = new Goods("M", 20, SaleStatus.SALE);
            goods19_2.changeItem(item19);
            goodsRepository.save(goods19_2);

            Goods goods19_3 = new Goods("L", 0, SaleStatus.SALE);
            goods19_3.changeItem(item19);
            goodsRepository.save(goods19_3);

            Goods goods20 = new Goods("S", 11, SaleStatus.SALE);
            goods20.changeItem(item20);
            goodsRepository.save(goods20);

            Goods goods20_2 = new Goods("M", 20, SaleStatus.SALE);
            goods20_2.changeItem(item20);
            goodsRepository.save(goods20_2);

            Goods goods20_3 = new Goods("L", 30, SaleStatus.SALE);
            goods20_3.changeItem(item20);
            goodsRepository.save(goods20_3);

            /*긴팔 티셔츠 옵션*/
            Goods goods21 = new Goods("S", 5, SaleStatus.SALE);
            goods21.changeItem(item21);
            goodsRepository.save(goods21);

            Goods goods21_2 = new Goods("M", 20, SaleStatus.SALE);
            goods21_2.changeItem(item21);
            goodsRepository.save(goods21_2);

            Goods goods21_3 = new Goods("L", 0, SaleStatus.SALE);
            goods21_3.changeItem(item21);
            goodsRepository.save(goods21_3);

            Goods goods22 = new Goods("S", 11, SaleStatus.SALE);
            goods22.changeItem(item22);
            goodsRepository.save(goods22);

            Goods goods22_2 = new Goods("M", 20, SaleStatus.SALE);
            goods22_2.changeItem(item22);
            goodsRepository.save(goods22_2);

            Goods goods22_3 = new Goods("L", 30, SaleStatus.SALE);
            goods22_3.changeItem(item22);
            goodsRepository.save(goods22_3);

            Goods goods23 = new Goods("S", 5, SaleStatus.SALE);
            goods23.changeItem(item23);
            goodsRepository.save(goods23);

            Goods goods23_2 = new Goods("M", 20, SaleStatus.SALE);
            goods23_2.changeItem(item23);
            goodsRepository.save(goods23_2);

            Goods goods23_3 = new Goods("L", 0, SaleStatus.SALE);
            goods23_3.changeItem(item23);
            goodsRepository.save(goods23_3);

            Goods goods24 = new Goods("S", 11, SaleStatus.SALE);
            goods24.changeItem(item24);
            goodsRepository.save(goods24);

            Goods goods24_2 = new Goods("M", 20, SaleStatus.SALE);
            goods24_2.changeItem(item24);
            goodsRepository.save(goods24_2);

            Goods goods24_3 = new Goods("L", 30, SaleStatus.SALE);
            goods24_3.changeItem(item24);
            goodsRepository.save(goods24_3);

            Goods goods25 = new Goods("S", 5, SaleStatus.SALE);
            goods25.changeItem(item25);
            goodsRepository.save(goods25);

            Goods goods25_2 = new Goods("M", 20, SaleStatus.SALE);
            goods25_2.changeItem(item25);
            goodsRepository.save(goods25_2);

            Goods goods25_3 = new Goods("L", 0, SaleStatus.SALE);
            goods25_3.changeItem(item25);
            goodsRepository.save(goods25_3);

            Goods goods26 = new Goods("S", 11, SaleStatus.SALE);
            goods26.changeItem(item26);
            goodsRepository.save(goods26);

            Goods goods26_2 = new Goods("M", 20, SaleStatus.SALE);
            goods26_2.changeItem(item26);
            goodsRepository.save(goods26_2);

            Goods goods26_3 = new Goods("L", 30, SaleStatus.SALE);
            goods26_3.changeItem(item26);
            goodsRepository.save(goods26_3);

            Goods goods27 = new Goods("S", 5, SaleStatus.SALE);
            goods27.changeItem(item27);
            goodsRepository.save(goods27);

            Goods goods27_2 = new Goods("M", 20, SaleStatus.SALE);
            goods27_2.changeItem(item27);
            goodsRepository.save(goods27_2);

            Goods goods27_3 = new Goods("L", 0, SaleStatus.SALE);
            goods27_3.changeItem(item27);
            goodsRepository.save(goods27_3);

            Goods goods28 = new Goods("S", 11, SaleStatus.SALE);
            goods28.changeItem(item28);
            goodsRepository.save(goods28);

            Goods goods28_2 = new Goods("M", 20, SaleStatus.SALE);
            goods28_2.changeItem(item28);
            goodsRepository.save(goods28_2);

            Goods goods28_3 = new Goods("L", 30, SaleStatus.SALE);
            goods28_3.changeItem(item28);
            goodsRepository.save(goods28_3);

            Goods goods29 = new Goods("S", 5, SaleStatus.SALE);
            goods29.changeItem(item29);
            goodsRepository.save(goods29);

            Goods goods29_2 = new Goods("M", 20, SaleStatus.SALE);
            goods29_2.changeItem(item29);
            goodsRepository.save(goods29_2);

            Goods goods29_3 = new Goods("L", 0, SaleStatus.SALE);
            goods29_3.changeItem(item29);
            goodsRepository.save(goods29_3);

            Goods goods30 = new Goods("S", 11, SaleStatus.SALE);
            goods30.changeItem(item30);
            goodsRepository.save(goods30);

            Goods goods30_2 = new Goods("M", 20, SaleStatus.SALE);
            goods30_2.changeItem(item30);
            goodsRepository.save(goods30_2);

            Goods goods30_3 = new Goods("L", 30, SaleStatus.SALE);
            goods30_3.changeItem(item30);
            goodsRepository.save(goods30_3);

            /*맨투맨/스웨트셔츠 옵션*/
            Goods goods31 = new Goods("S", 5, SaleStatus.SALE);
            goods31.changeItem(item31);
            goodsRepository.save(goods31);

            Goods goods31_2 = new Goods("M", 20, SaleStatus.SALE);
            goods31_2.changeItem(item31);
            goodsRepository.save(goods31_2);

            Goods goods31_3 = new Goods("L", 0, SaleStatus.SALE);
            goods31_3.changeItem(item31);
            goodsRepository.save(goods31_3);

            Goods goods32 = new Goods("S", 11, SaleStatus.SALE);
            goods32.changeItem(item32);
            goodsRepository.save(goods32);

            Goods goods32_2 = new Goods("M", 20, SaleStatus.SALE);
            goods32_2.changeItem(item32);
            goodsRepository.save(goods32_2);

            Goods goods32_3 = new Goods("L", 30, SaleStatus.SALE);
            goods32_3.changeItem(item32);
            goodsRepository.save(goods32_3);

            Goods goods33 = new Goods("S", 5, SaleStatus.SALE);
            goods33.changeItem(item33);
            goodsRepository.save(goods33);

            Goods goods33_2 = new Goods("M", 20, SaleStatus.SALE);
            goods33_2.changeItem(item33);
            goodsRepository.save(goods33_2);

            Goods goods33_3 = new Goods("L", 0, SaleStatus.SALE);
            goods33_3.changeItem(item33);
            goodsRepository.save(goods33_3);

            Goods goods34 = new Goods("S", 11, SaleStatus.SALE);
            goods34.changeItem(item34);
            goodsRepository.save(goods34);

            Goods goods34_2 = new Goods("M", 20, SaleStatus.SALE);
            goods34_2.changeItem(item34);
            goodsRepository.save(goods34_2);

            Goods goods34_3 = new Goods("L", 30, SaleStatus.SALE);
            goods34_3.changeItem(item34);
            goodsRepository.save(goods34_3);

            Goods goods35 = new Goods("S", 5, SaleStatus.SALE);
            goods35.changeItem(item35);
            goodsRepository.save(goods35);

            Goods goods35_2 = new Goods("M", 20, SaleStatus.SALE);
            goods35_2.changeItem(item35);
            goodsRepository.save(goods35_2);

            Goods goods35_3 = new Goods("L", 0, SaleStatus.SALE);
            goods35_3.changeItem(item35);
            goodsRepository.save(goods35_3);

            Goods goods36 = new Goods("S", 11, SaleStatus.SALE);
            goods36.changeItem(item36);
            goodsRepository.save(goods36);

            Goods goods36_2 = new Goods("M", 20, SaleStatus.SALE);
            goods36_2.changeItem(item36);
            goodsRepository.save(goods36_2);

            Goods goods36_3 = new Goods("L", 30, SaleStatus.SALE);
            goods36_3.changeItem(item36);
            goodsRepository.save(goods36_3);

            Goods goods37 = new Goods("S", 5, SaleStatus.SALE);
            goods37.changeItem(item37);
            goodsRepository.save(goods37);

            Goods goods37_2 = new Goods("M", 20, SaleStatus.SALE);
            goods37_2.changeItem(item37);
            goodsRepository.save(goods37_2);

            Goods goods37_3 = new Goods("L", 0, SaleStatus.SALE);
            goods37_3.changeItem(item37);
            goodsRepository.save(goods37_3);

            Goods goods38 = new Goods("S", 11, SaleStatus.SALE);
            goods38.changeItem(item38);
            goodsRepository.save(goods38);

            Goods goods38_2 = new Goods("M", 20, SaleStatus.SALE);
            goods38_2.changeItem(item38);
            goodsRepository.save(goods38_2);

            Goods goods38_3 = new Goods("L", 30, SaleStatus.SALE);
            goods38_3.changeItem(item38);
            goodsRepository.save(goods38_3);

            Goods goods39 = new Goods("S", 5, SaleStatus.SALE);
            goods39.changeItem(item39);
            goodsRepository.save(goods39);

            Goods goods39_2 = new Goods("M", 20, SaleStatus.SALE);
            goods39_2.changeItem(item39);
            goodsRepository.save(goods39_2);

            Goods goods39_3 = new Goods("L", 0, SaleStatus.SALE);
            goods39_3.changeItem(item39);
            goodsRepository.save(goods39_3);

            Goods goods40 = new Goods("S", 11, SaleStatus.SALE);
            goods40.changeItem(item40);
            goodsRepository.save(goods40);

            Goods goods40_2 = new Goods("M", 20, SaleStatus.SALE);
            goods40_2.changeItem(item40);
            goodsRepository.save(goods40_2);

            Goods goods40_3 = new Goods("L", 30, SaleStatus.SALE);
            goods40_3.changeItem(item40);
            goodsRepository.save(goods40_3);

            /*맨투맨/스웨트셔츠 옵션*/
            Goods goods41 = new Goods("S", 5, SaleStatus.SALE);
            goods41.changeItem(item41);
            goodsRepository.save(goods41);

            Goods goods41_2 = new Goods("M", 20, SaleStatus.SALE);
            goods41_2.changeItem(item41);
            goodsRepository.save(goods41_2);

            Goods goods41_3 = new Goods("L", 0, SaleStatus.SALE);
            goods41_3.changeItem(item41);
            goodsRepository.save(goods41_3);

            Goods goods42 = new Goods("S", 11, SaleStatus.SALE);
            goods42.changeItem(item42);
            goodsRepository.save(goods42);

            Goods goods42_2 = new Goods("M", 20, SaleStatus.SALE);
            goods42_2.changeItem(item42);
            goodsRepository.save(goods42_2);

            Goods goods42_3 = new Goods("L", 30, SaleStatus.SALE);
            goods42_3.changeItem(item42);
            goodsRepository.save(goods42_3);

            Goods goods43 = new Goods("S", 5, SaleStatus.SALE);
            goods43.changeItem(item43);
            goodsRepository.save(goods43);

            Goods goods43_2 = new Goods("M", 20, SaleStatus.SALE);
            goods43_2.changeItem(item43);
            goodsRepository.save(goods43_2);

            Goods goods43_3 = new Goods("L", 0, SaleStatus.SALE);
            goods43_3.changeItem(item43);
            goodsRepository.save(goods43_3);

            Goods goods44 = new Goods("S", 11, SaleStatus.SALE);
            goods44.changeItem(item44);
            goodsRepository.save(goods44);

            Goods goods44_2 = new Goods("M", 20, SaleStatus.SALE);
            goods44_2.changeItem(item44);
            goodsRepository.save(goods44_2);

            Goods goods44_3 = new Goods("L", 30, SaleStatus.SALE);
            goods44_3.changeItem(item44);
            goodsRepository.save(goods44_3);

            Goods goods45 = new Goods("S", 5, SaleStatus.SALE);
            goods45.changeItem(item45);
            goodsRepository.save(goods45);

            Goods goods45_2 = new Goods("M", 20, SaleStatus.SALE);
            goods45_2.changeItem(item45);
            goodsRepository.save(goods45_2);

            Goods goods45_3 = new Goods("L", 0, SaleStatus.SALE);
            goods45_3.changeItem(item45);
            goodsRepository.save(goods45_3);

            Goods goods46 = new Goods("S", 11, SaleStatus.SALE);
            goods46.changeItem(item46);
            goodsRepository.save(goods46);

            Goods goods46_2 = new Goods("M", 20, SaleStatus.SALE);
            goods46_2.changeItem(item46);
            goodsRepository.save(goods46_2);

            Goods goods46_3 = new Goods("L", 30, SaleStatus.SALE);
            goods46_3.changeItem(item46);
            goodsRepository.save(goods46_3);

            Goods goods47 = new Goods("S", 5, SaleStatus.SALE);
            goods47.changeItem(item47);
            goodsRepository.save(goods47);

            Goods goods47_2 = new Goods("M", 20, SaleStatus.SALE);
            goods47_2.changeItem(item47);
            goodsRepository.save(goods47_2);

            Goods goods47_3 = new Goods("L", 0, SaleStatus.SALE);
            goods47_3.changeItem(item47);
            goodsRepository.save(goods47_3);

            Goods goods48 = new Goods("S", 11, SaleStatus.SALE);
            goods48.changeItem(item48);
            goodsRepository.save(goods48);

            Goods goods48_2 = new Goods("M", 20, SaleStatus.SALE);
            goods48_2.changeItem(item48);
            goodsRepository.save(goods48_2);

            Goods goods48_3 = new Goods("L", 30, SaleStatus.SALE);
            goods48_3.changeItem(item48);
            goodsRepository.save(goods48_3);

            Goods goods49 = new Goods("S", 5, SaleStatus.SALE);
            goods49.changeItem(item49);
            goodsRepository.save(goods49);

            Goods goods49_2 = new Goods("M", 20, SaleStatus.SALE);
            goods49_2.changeItem(item49);
            goodsRepository.save(goods49_2);

            Goods goods49_3 = new Goods("L", 0, SaleStatus.SALE);
            goods49_3.changeItem(item49);
            goodsRepository.save(goods49_3);

            Goods goods50 = new Goods("S", 11, SaleStatus.SALE);
            goods50.changeItem(item50);
            goodsRepository.save(goods50);

            Goods goods50_2 = new Goods("M", 20, SaleStatus.SALE);
            goods50_2.changeItem(item50);
            goodsRepository.save(goods50_2);

            Goods goods50_3 = new Goods("L", 30, SaleStatus.SALE);
            goods50_3.changeItem(item50);
            goodsRepository.save(goods50_3);

            /*데님 팬츠 옵션*/
            Goods goods51 = new Goods("S", 5, SaleStatus.SALE);
            goods51.changeItem(item51);
            goodsRepository.save(goods51);

            Goods goods51_2 = new Goods("M", 20, SaleStatus.SALE);
            goods51_2.changeItem(item51);
            goodsRepository.save(goods51_2);

            Goods goods51_3 = new Goods("L", 0, SaleStatus.SALE);
            goods51_3.changeItem(item51);
            goodsRepository.save(goods51_3);

            Goods goods52 = new Goods("S", 11, SaleStatus.SALE);
            goods52.changeItem(item52);
            goodsRepository.save(goods52);

            Goods goods52_2 = new Goods("M", 20, SaleStatus.SALE);
            goods52_2.changeItem(item52);
            goodsRepository.save(goods52_2);

            Goods goods52_3 = new Goods("L", 30, SaleStatus.SALE);
            goods52_3.changeItem(item52);
            goodsRepository.save(goods52_3);

            Goods goods53 = new Goods("S", 5, SaleStatus.SALE);
            goods53.changeItem(item53);
            goodsRepository.save(goods53);

            Goods goods53_2 = new Goods("M", 20, SaleStatus.SALE);
            goods53_2.changeItem(item53);
            goodsRepository.save(goods53_2);

            Goods goods53_3 = new Goods("L", 0, SaleStatus.SALE);
            goods53_3.changeItem(item53);
            goodsRepository.save(goods53_3);

            Goods goods54 = new Goods("S", 11, SaleStatus.SALE);
            goods54.changeItem(item54);
            goodsRepository.save(goods54);

            Goods goods54_2 = new Goods("M", 20, SaleStatus.SALE);
            goods54_2.changeItem(item54);
            goodsRepository.save(goods54_2);

            Goods goods54_3 = new Goods("L", 30, SaleStatus.SALE);
            goods54_3.changeItem(item54);
            goodsRepository.save(goods54_3);

            Goods goods55 = new Goods("S", 5, SaleStatus.SALE);
            goods55.changeItem(item55);
            goodsRepository.save(goods55);

            Goods goods55_2 = new Goods("M", 20, SaleStatus.SALE);
            goods55_2.changeItem(item55);
            goodsRepository.save(goods55_2);

            Goods goods55_3 = new Goods("L", 0, SaleStatus.SALE);
            goods55_3.changeItem(item55);
            goodsRepository.save(goods55_3);

            Goods goods56 = new Goods("S", 11, SaleStatus.SALE);
            goods56.changeItem(item56);
            goodsRepository.save(goods56);

            Goods goods56_2 = new Goods("M", 20, SaleStatus.SALE);
            goods56_2.changeItem(item56);
            goodsRepository.save(goods56_2);

            Goods goods56_3 = new Goods("L", 30, SaleStatus.SALE);
            goods56_3.changeItem(item56);
            goodsRepository.save(goods56_3);

            Goods goods57 = new Goods("S", 5, SaleStatus.SALE);
            goods57.changeItem(item57);
            goodsRepository.save(goods57);

            Goods goods57_2 = new Goods("M", 20, SaleStatus.SALE);
            goods57_2.changeItem(item57);
            goodsRepository.save(goods57_2);

            Goods goods57_3 = new Goods("L", 0, SaleStatus.SALE);
            goods57_3.changeItem(item57);
            goodsRepository.save(goods57_3);

            Goods goods58 = new Goods("S", 11, SaleStatus.SALE);
            goods58.changeItem(item58);
            goodsRepository.save(goods58);

            Goods goods58_2 = new Goods("M", 20, SaleStatus.SALE);
            goods58_2.changeItem(item58);
            goodsRepository.save(goods58_2);

            Goods goods58_3 = new Goods("L", 30, SaleStatus.SALE);
            goods58_3.changeItem(item58);
            goodsRepository.save(goods58_3);

            Goods goods59 = new Goods("S", 5, SaleStatus.SALE);
            goods59.changeItem(item59);
            goodsRepository.save(goods59);

            Goods goods59_2 = new Goods("M", 20, SaleStatus.SALE);
            goods59_2.changeItem(item59);
            goodsRepository.save(goods59_2);

            Goods goods59_3 = new Goods("L", 0, SaleStatus.SALE);
            goods59_3.changeItem(item59);
            goodsRepository.save(goods59_3);

            Goods goods60 = new Goods("S", 11, SaleStatus.SALE);
            goods60.changeItem(item60);
            goodsRepository.save(goods60);

            Goods goods60_2 = new Goods("M", 20, SaleStatus.SALE);
            goods60_2.changeItem(item60);
            goodsRepository.save(goods60_2);

            Goods goods60_3 = new Goods("L", 30, SaleStatus.SALE);
            goods60_3.changeItem(item60);
            goodsRepository.save(goods60_3);

            /*데님 팬츠 옵션*/
            Goods goods61 = new Goods("S", 5, SaleStatus.SALE);
            goods61.changeItem(item61);
            goodsRepository.save(goods61);

            Goods goods61_2 = new Goods("M", 20, SaleStatus.SALE);
            goods61_2.changeItem(item61);
            goodsRepository.save(goods61_2);

            Goods goods61_3 = new Goods("L", 0, SaleStatus.SALE);
            goods61_3.changeItem(item61);
            goodsRepository.save(goods61_3);

            Goods goods62 = new Goods("S", 11, SaleStatus.SALE);
            goods62.changeItem(item62);
            goodsRepository.save(goods62);

            Goods goods62_2 = new Goods("M", 20, SaleStatus.SALE);
            goods62_2.changeItem(item62);
            goodsRepository.save(goods62_2);

            Goods goods62_3 = new Goods("L", 30, SaleStatus.SALE);
            goods62_3.changeItem(item62);
            goodsRepository.save(goods62_3);

            Goods goods63 = new Goods("S", 5, SaleStatus.SALE);
            goods63.changeItem(item63);
            goodsRepository.save(goods63);

            Goods goods63_2 = new Goods("M", 20, SaleStatus.SALE);
            goods63_2.changeItem(item63);
            goodsRepository.save(goods63_2);

            Goods goods63_3 = new Goods("L", 0, SaleStatus.SALE);
            goods63_3.changeItem(item63);
            goodsRepository.save(goods63_3);

            Goods goods64 = new Goods("S", 11, SaleStatus.SALE);
            goods64.changeItem(item64);
            goodsRepository.save(goods64);

            Goods goods64_2 = new Goods("M", 20, SaleStatus.SALE);
            goods64_2.changeItem(item64);
            goodsRepository.save(goods64_2);

            Goods goods64_3 = new Goods("L", 30, SaleStatus.SALE);
            goods64_3.changeItem(item64);
            goodsRepository.save(goods64_3);

            Goods goods65 = new Goods("S", 5, SaleStatus.SALE);
            goods65.changeItem(item65);
            goodsRepository.save(goods65);

            Goods goods65_2 = new Goods("M", 20, SaleStatus.SALE);
            goods65_2.changeItem(item65);
            goodsRepository.save(goods65_2);

            Goods goods65_3 = new Goods("L", 0, SaleStatus.SALE);
            goods65_3.changeItem(item65);
            goodsRepository.save(goods65_3);

            /*슈트 팬츠/슬랙스 옵션*/
            Goods goods66 = new Goods("S", 5, SaleStatus.SALE);
            goods66.changeItem(item66);
            goodsRepository.save(goods66);

            Goods goods66_2 = new Goods("M", 20, SaleStatus.SALE);
            goods66_2.changeItem(item66);
            goodsRepository.save(goods66_2);

            Goods goods66_3 = new Goods("L", 0, SaleStatus.SALE);
            goods66_3.changeItem(item66);
            goodsRepository.save(goods66_3);

            Goods goods67 = new Goods("S", 11, SaleStatus.SALE);
            goods67.changeItem(item67);
            goodsRepository.save(goods67);

            Goods goods67_2 = new Goods("M", 20, SaleStatus.SALE);
            goods67_2.changeItem(item67);
            goodsRepository.save(goods67_2);

            Goods goods67_3 = new Goods("L", 30, SaleStatus.SALE);
            goods67_3.changeItem(item67);
            goodsRepository.save(goods67_3);

            Goods goods68 = new Goods("S", 5, SaleStatus.SALE);
            goods68.changeItem(item68);
            goodsRepository.save(goods68);

            Goods goods68_2 = new Goods("M", 20, SaleStatus.SALE);
            goods68_2.changeItem(item68);
            goodsRepository.save(goods68_2);

            Goods goods68_3 = new Goods("L", 0, SaleStatus.SALE);
            goods68_3.changeItem(item68);
            goodsRepository.save(goods68_3);

            Goods goods69 = new Goods("S", 5, SaleStatus.SALE);
            goods69.changeItem(item69);
            goodsRepository.save(goods69);

            Goods goods69_2 = new Goods("M", 20, SaleStatus.SALE);
            goods69_2.changeItem(item69);
            goodsRepository.save(goods69_2);

            Goods goods69_3 = new Goods("L", 0, SaleStatus.SALE);
            goods69_3.changeItem(item69);
            goodsRepository.save(goods69_3);

            Goods goods70 = new Goods("S", 11, SaleStatus.SALE);
            goods70.changeItem(item70);
            goodsRepository.save(goods70);

            Goods goods70_2 = new Goods("M", 20, SaleStatus.SALE);
            goods70_2.changeItem(item70);
            goodsRepository.save(goods70_2);

            Goods goods70_3 = new Goods("L", 30, SaleStatus.SALE);
            goods70_3.changeItem(item70);
            goodsRepository.save(goods70_3);

            /*숏 팬츠 옵션*/
            Goods goods71 = new Goods("S", 5, SaleStatus.SALE);
            goods71.changeItem(item71);
            goodsRepository.save(goods71);

            Goods goods71_2 = new Goods("M", 20, SaleStatus.SALE);
            goods71_2.changeItem(item71);
            goodsRepository.save(goods71_2);

            Goods goods71_3 = new Goods("L", 0, SaleStatus.SALE);
            goods71_3.changeItem(item71);
            goodsRepository.save(goods71_3);

            Goods goods72 = new Goods("S", 11, SaleStatus.SALE);
            goods72.changeItem(item72);
            goodsRepository.save(goods72);

            Goods goods72_2 = new Goods("M", 20, SaleStatus.SALE);
            goods72_2.changeItem(item72);
            goodsRepository.save(goods72_2);

            Goods goods72_3 = new Goods("L", 30, SaleStatus.SALE);
            goods72_3.changeItem(item72);
            goodsRepository.save(goods72_3);

            Goods goods73 = new Goods("S", 5, SaleStatus.SALE);
            goods73.changeItem(item73);
            goodsRepository.save(goods73);

            Goods goods73_2 = new Goods("M", 20, SaleStatus.SALE);
            goods73_2.changeItem(item73);
            goodsRepository.save(goods73_2);

            Goods goods73_3 = new Goods("L", 0, SaleStatus.SALE);
            goods73_3.changeItem(item73);
            goodsRepository.save(goods73_3);

            Goods goods74 = new Goods("S", 5, SaleStatus.SALE);
            goods74.changeItem(item74);
            goodsRepository.save(goods74);

            Goods goods74_2 = new Goods("M", 20, SaleStatus.SALE);
            goods74_2.changeItem(item74);
            goodsRepository.save(goods74_2);

            Goods goods74_3 = new Goods("L", 0, SaleStatus.SALE);
            goods74_3.changeItem(item74);
            goodsRepository.save(goods74_3);

            Goods goods75 = new Goods("S", 11, SaleStatus.SALE);
            goods75.changeItem(item75);
            goodsRepository.save(goods75);

            Goods goods75_2 = new Goods("M", 20, SaleStatus.SALE);
            goods75_2.changeItem(item75);
            goodsRepository.save(goods75_2);

            Goods goods75_3 = new Goods("L", 30, SaleStatus.SALE);
            goods75_3.changeItem(item75);
            goodsRepository.save(goods75_3);

            /*슈트/블레이저 자켓 옵션*/
            Goods goods76 = new Goods("S", 5, SaleStatus.SALE);
            goods76.changeItem(item76);
            goodsRepository.save(goods76);

            Goods goods76_2 = new Goods("M", 20, SaleStatus.SALE);
            goods76_2.changeItem(item76);
            goodsRepository.save(goods76_2);

            Goods goods76_3 = new Goods("L", 0, SaleStatus.SALE);
            goods76_3.changeItem(item76);
            goodsRepository.save(goods76_3);

            Goods goods77 = new Goods("S", 11, SaleStatus.SALE);
            goods77.changeItem(item77);
            goodsRepository.save(goods77);

            Goods goods77_2 = new Goods("M", 20, SaleStatus.SALE);
            goods77_2.changeItem(item77);
            goodsRepository.save(goods77_2);

            Goods goods77_3 = new Goods("L", 30, SaleStatus.SALE);
            goods77_3.changeItem(item77);
            goodsRepository.save(goods77_3);

            Goods goods78 = new Goods("S", 5, SaleStatus.SALE);
            goods78.changeItem(item78);
            goodsRepository.save(goods78);

            Goods goods78_2 = new Goods("M", 20, SaleStatus.SALE);
            goods78_2.changeItem(item78);
            goodsRepository.save(goods78_2);

            Goods goods78_3 = new Goods("L", 0, SaleStatus.SALE);
            goods78_3.changeItem(item78);
            goodsRepository.save(goods78_3);

            Goods goods79 = new Goods("S", 5, SaleStatus.SALE);
            goods79.changeItem(item79);
            goodsRepository.save(goods79);

            Goods goods79_2 = new Goods("M", 20, SaleStatus.SALE);
            goods79_2.changeItem(item79);
            goodsRepository.save(goods79_2);

            Goods goods79_3 = new Goods("L", 0, SaleStatus.SALE);
            goods79_3.changeItem(item79);
            goodsRepository.save(goods79_3);

            Goods goods80 = new Goods("S", 11, SaleStatus.SALE);
            goods80.changeItem(item80);
            goodsRepository.save(goods80);

            Goods goods80_2 = new Goods("M", 20, SaleStatus.SALE);
            goods80_2.changeItem(item80);
            goodsRepository.save(goods80_2);

            Goods goods80_3 = new Goods("L", 30, SaleStatus.SALE);
            goods80_3.changeItem(item80);
            goodsRepository.save(goods80_3);


            /*--------------------------------태그----------------------------------------*/
            /*반팔 티셔츠 태그*/
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

            String tag7 = "#반팔티셔츠#오버핏반팔#로고";
            tagService.addNewTag(tag7, item7);

            String tag8 = "#로고#로고티셔츠#반팔#반팔티#반팔티셔츠#오버핏";
            tagService.addNewTag(tag8, item8);

            String tag9 = "#반팔티셔츠#오버핏반팔#로고";
            tagService.addNewTag(tag9, item9);

            String tag10 = "#로고#로고티셔츠#반팔#반팔티#반팔티셔츠#오버핏";
            tagService.addNewTag(tag10, item10);

            /*긴팔 티셔츠 태그*/
            String tag11 = "#기본티#긴팔티#라운드넥#루즈핏#면티셔츠#무지티#홈웨어컬렉션";
            tagService.addNewTag(tag11, item11);

            String tag12 = "#기본티#긴팔티#라운드넥#루즈핏#면티셔츠#무지티#홈웨어컬렉션";
            tagService.addNewTag(tag12, item12);

            String tag13 = "#긴팔티#롱슬리브#엔젤로고#체크#루즈핏#스트라이프";
            tagService.addNewTag(tag13, item13);

            String tag14 = "#기본티#긴팔티#레이어드티#레이어드티셔츠#롱슬리브#쭉티#티셔츠";
            tagService.addNewTag(tag14, item14);

            String tag15 = "#그래픽#긴팔티#오버핏#티셔츠#긴팔티셔츠#로고#루즈핏";
            tagService.addNewTag(tag15, item15);

            String tag16 = "#긴팔티#오버핏#자수#프린팅#16수";
            tagService.addNewTag(tag16, item16);

            String tag17 = "#크루넥#롱슬리브#스트라이프";
            tagService.addNewTag(tag17, item17);

            String tag18 = "#로고#롱슬리브#스트라이프#오버핏#데일리룩";
            tagService.addNewTag(tag18, item18);

            String tag19 = "#롱슬리브#긴팔티셔츠#레이어드#레이어드티셔츠";
            tagService.addNewTag(tag19, item19);

            String tag20 = "#롱슬리브#긴팔티셔츠";
            tagService.addNewTag(tag20, item20);

            /*셔츠/블라우스 태그*/
            String tag21 = "#롱슬리브#긴팔티셔츠#레이어드#레이어드티셔츠";
            tagService.addNewTag(tag21, item21);

            String tag22 = "#롱슬리브#긴팔티셔츠";
            tagService.addNewTag(tag22, item22);

            String tag23 = "#셔츠#오버핏셔츠#체크#체크무늬셔츠#체크셔츠";
            tagService.addNewTag(tag23, item23);

            String tag24 = "#남방#셔츠#오버사이즈셔츠#체크#체크셔츠#오버핏셔츠#긴팔셔츠";
            tagService.addNewTag(tag24, item24);

            String tag25 = "#레이어드#오버셔츠#오버핏#옥스포드";
            tagService.addNewTag(tag25, item25);

            String tag26 = "#오버핏#린넨셔츠#오버핏셔츠#여름셔츠#폴로셔츠#반팔#반팔셔츠";
            tagService.addNewTag(tag26, item26);

            String tag27 = "#남방#셔츠#스트라이프#스트라이프셔츠#체크셔츠#긴팔셔츠#오버핏셔츠";
            tagService.addNewTag(tag27, item27);

            String tag28 = "#기본#베이직#옥스포드셔츠#긴팔셔츠";
            tagService.addNewTag(tag28, item28);

            String tag29 = "#남자셔츠#미니멀#스트라이프#스트라이프셔츠#오버핏#커플셔츠#포켓셔츠";
            tagService.addNewTag(tag29, item29);

            String tag30 = "#남자셔츠#셔츠#스트라이프셔츠#줄무늬셔츠#캐쥬얼셔츠";
            tagService.addNewTag(tag30, item30);

            /*맨투맨/스웨트셔츠 태그*/
            String tag31 = "#맨투맨#오버핏#로고#스웨트셔츠";
            tagService.addNewTag(tag31, item31);

            String tag32 = "#크루넥#맨투맨#헤리티지캐주얼";
            tagService.addNewTag(tag32, item32);

            String tag33 = "#기본#베이직#스웨트셋업#스웨트컬렉션#이지웨어#트레이닝복#스쿨룩";
            tagService.addNewTag(tag33, item33);

            String tag34 = "#스웨트셔츠#크루넥#오버핏맨투맨#루즈핏#맨투맨";
            tagService.addNewTag(tag34, item34);

            String tag35 = "#로고#로고스웨트";
            tagService.addNewTag(tag35, item35);

            String tag36 = "#맨투맨#스웻셔츠#오버사이즈#헤비코튼";
            tagService.addNewTag(tag36, item36);

            String tag37 = "#기모맨투맨";
            tagService.addNewTag(tag37, item37);

            String tag38 = "#로고#로고맨투맨#로고스웨트#맨투맨#크루넥";
            tagService.addNewTag(tag38, item38);

            String tag39 = "#맨투맨#맨투맨추천#크루넥#기본맨투맨#기본크루넥#F로고#로고";
            tagService.addNewTag(tag39, item39);

            String tag40 = "#맨투맨#스웨트셔츠#스웻셔츠#맨투맨티셔츠#맨투맨티#기모맨투맨#로고";
            tagService.addNewTag(tag40, item40);

            /*니트/스웨터 태그*/
            String tag41 = "#모크넥#목폴라#반폴라#터틀넥";
            tagService.addNewTag(tag41, item41);

            String tag42 = "#스웨터#겨울니트#니트#라운드니트#남자니트#오버핏니트#가디건";
            tagService.addNewTag(tag42, item42);

            String tag43 = "#니트#미니멀#화란#울니트#오버핏니트#겨울니트#남자니트";
            tagService.addNewTag(tag43, item43);

            String tag44 = "#스웨터#라운드니트#데일리#남자니트#울니트#니트#오버핏니트";
            tagService.addNewTag(tag44, item44);

            String tag45 = "#긴팔티#니트#소프트코튼#오버핏#피마코튼#와플#베이식";
            tagService.addNewTag(tag45, item45);

            String tag46 = "#니트#오버핏니트#남자니트#롱슬리브#스웨터#라운드니트";
            tagService.addNewTag(tag46, item46);

            String tag47 = "#긴팔니트#유넥#니트#유넥니트#니트컬렉션#베이식";
            tagService.addNewTag(tag47, item47);

            String tag48 = "#오버핏=#스트라이프#믹스스트라이프#드롭숄더#베이식";
            tagService.addNewTag(tag48, item48);

            String tag49 = "#니트#목폴라#터틀넥";
            tagService.addNewTag(tag49, item49);

            String tag50 = "=#니트#울니트#라운드니트#온유니트#워머니트#폴로넥#배색#캐리오버";
            tagService.addNewTag(tag50, item50);

            String tag51 = "#데님팬츠#와이드#와이드데님#와이드청바지#와이드팬츠#청바지#데일리룩";
            tagService.addNewTag(tag51, item51);

            String tag52 = "#데님#스트레이트#와이드#청바지#크림진#데일리룩#크롭진";
            tagService.addNewTag(tag52, item52);

            String tag53 = "#데님팬츠#와이드#와이드데님#와이드청바지#와이드팬츠#청바지#데일리룩";
            tagService.addNewTag(tag53, item53);

            String tag54 = "#데님#워싱진#중청#청바지#크롭#펑크타운#데일리룩";
            tagService.addNewTag(tag54, item54);

            String tag55 = "#데님#생지#스트레이트#크롭핏컬렉션";
            tagService.addNewTag(tag55, item55);

            String tag56 = "#데님컬렉션#데일리팬츠#슬림핏#워싱진#청바지#캐주얼#크롭#캐주얼팬츠";
            tagService.addNewTag(tag56, item56);

            String tag57 = "#청바지#블랙진#워싱진#와이드#데님팬츠#데일리룩#와이드데님";
            tagService.addNewTag(tag57, item57);

            String tag58 = "#데님#레직기#블랙진#크롭페이퍼드#테이퍼드";
            tagService.addNewTag(tag58, item58);

            String tag59 = "#데님#바지#와이드핏#페인트#오빠룩#데일리룩";
            tagService.addNewTag(tag59, item59);

            String tag60 = "#데님#블랙진#청바지#흑청데님#흑청진#크롭#크롭진";
            tagService.addNewTag(tag60, item60);

            /*코튼팬츠 태그*/
            String tag61 = "#데님팬츠#와이드면팬츠#와이드코튼팬츠#와이드팬츠#와이드#베이지";
            tagService.addNewTag(tag61, item61);

            String tag62 = "#리넨#리넨팬츠#린넨#린넨팬츠#린넨바지#밴딩팬츠#팬츠";
            tagService.addNewTag(tag62, item62);

            String tag63 = "#면바지#스트레치#치노팬츠#캐주얼팬츠#면#테이퍼드";
            tagService.addNewTag(tag63, item63);

            String tag64 = "#남자면바지#남자면팬츠#면#워싱면바지#테이퍼드핏#테이퍼드핏팬츠#테이퍼드";
            tagService.addNewTag(tag64, item64);

            String tag65 = "#벌룬팬츠#벌룬핏#와이드팬츠#카고바지#카고팬츠#팬츠#벌룬";
            tagService.addNewTag(tag65, item65);

            /*코튼팬츠 태그*/
            String tag66 = "#밴딩슬랙스#셋업팬츠#정장바지#퍼펙트슬랙스#밴딩#테이퍼드";
            tagService.addNewTag(tag66, item66);

            String tag67 = "#밴딩바지#밴딩슬랙스#밴딩팬츠#슬랙스#와이드#와이드슬랙스#와이드핏";
            tagService.addNewTag(tag67, item67);

            String tag68 = "#밴딩#세미와이드#슬랙스#와이드#와이드팬츠";
            tagService.addNewTag(tag68, item68);

            String tag69 = "#밴딩팬츠#와이드팬츠#코튼팬츠#트레이닝바지";
            tagService.addNewTag(tag69, item69);

            String tag70 = "#슬랙스#크롭#크롭슬랙스#크롭슬림핏슬랙스#테이퍼드#파츠슬랙스#스마트룩";
            tagService.addNewTag(tag70, item70);

            /*숏팬츠 태그*/
            String tag71 = "#반바지#쇼트팬츠#트레이닝#트레이닝복#바지#트레이닝바지#트레이닝팬츠";
            tagService.addNewTag(tag71, item71);

            String tag72 = "#반바지#벨티드팬츠#아웃도어#올데이쇼츠#하프팬츠#캐주얼팬츠";
            tagService.addNewTag(tag72, item72);

            String tag73 = "#밴딩반바지#밴딩팬츠#쇼츠팬츠#추리닝반바지";
            tagService.addNewTag(tag73, item73);

            String tag74 = "#경량반바지#경량쇼츠#운동복#트레이닝#트레이닝반바지#트레이닝쇼츠#피트니스쇼츠";
            tagService.addNewTag(tag74, item74);

            String tag75 = "#반바지#쇼츠#카고반바지#카고쇼츠#나일론반바지#트레이닝반바지";
            tagService.addNewTag(tag75, item75);

            /*슈트/블레이저 자켓 태그*/
            String tag76 = "#블레이저#셋업#정장#퍼펙트블레이저#재킷#테일러드재킷#투버튼";
            tagService.addNewTag(tag76, item76);

            String tag77 = "#글렌체크싱글블레이저#싱글블레이저#글렌체크#블레이져#싱글재킷#윷놀이룩#트렌디룩";
            tagService.addNewTag(tag77, item77);

            String tag78 = "#블레이저#블레이저자켓#블레이져#오버핏블레이저#자켓#싱글재킷";
            tagService.addNewTag(tag78, item78);

            String tag79 = "#블레이져#오버사이즈자켓#오버핏#자켓#트렌디룩#프리오더";
            tagService.addNewTag(tag79, item79);

            String tag80 = "#블레이져#아우터#체크블레이져";
            tagService.addNewTag(tag80, item80);
            /*--------------------------------브랜드----------------------------------------*/
            BufferedImage Banner1 = ImageIO.read(new File("src/main/resources/static/image/banner/banner0.png"));
            BufferedImage Banner2 = ImageIO.read(new File("src/main/resources/static/image/banner/banner1.png"));
            BufferedImage Banner3 = ImageIO.read(new File("src/main/resources/static/image/banner/banner2.png"));
            BufferedImage Banner4 = ImageIO.read(new File("src/main/resources/static/image/banner/banner0.png"));
            BufferedImage Banner5 = ImageIO.read(new File("src/main/resources/static/image/banner/banner1.png"));

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

            /*반팔 티셔츠 브랜드*/
            item.changeBrand(brand2);
            item2.changeBrand(brand0);
            item3.changeBrand(brand0);
            item4.changeBrand(brand2);
            item5.changeBrand(brand1);
            item6.changeBrand(brand1);
            item7.changeBrand(brand3);
            item8.changeBrand(brand3);
            item9.changeBrand(brand4);
            item10.changeBrand(brand4);
            /*긴팔 티셔츠 브랜드*/
            item11.changeBrand(brand0);
            item12.changeBrand(brand0);
            item13.changeBrand(brand1);
            item14.changeBrand(brand1);
            item15.changeBrand(brand2);
            item16.changeBrand(brand2);
            item17.changeBrand(brand3);
            item18.changeBrand(brand3);
            item19.changeBrand(brand4);
            item20.changeBrand(brand4);
            /*셔츠/불라우스 브랜드*/
            item21.changeBrand(brand0);
            item22.changeBrand(brand0);
            item23.changeBrand(brand1);
            item24.changeBrand(brand1);
            item25.changeBrand(brand2);
            item26.changeBrand(brand2);
            item27.changeBrand(brand3);
            item28.changeBrand(brand3);
            item29.changeBrand(brand4);
            item30.changeBrand(brand4);
            /*맨투맨/스웨트셔츠 브랜드*/
            item31.changeBrand(brand0);
            item32.changeBrand(brand0);
            item33.changeBrand(brand1);
            item34.changeBrand(brand1);
            item35.changeBrand(brand2);
            item36.changeBrand(brand2);
            item37.changeBrand(brand3);
            item38.changeBrand(brand3);
            item39.changeBrand(brand4);
            item40.changeBrand(brand4);
            /*니트/스웨터 브랜드*/
            item41.changeBrand(brand0);
            item42.changeBrand(brand0);
            item43.changeBrand(brand1);
            item44.changeBrand(brand1);
            item45.changeBrand(brand2);
            item46.changeBrand(brand2);
            item47.changeBrand(brand3);
            item48.changeBrand(brand3);
            item49.changeBrand(brand4);
            item50.changeBrand(brand4);
            /*데님 팬츠 브랜드*/
            item51.changeBrand(brand0);
            item52.changeBrand(brand0);
            item53.changeBrand(brand1);
            item54.changeBrand(brand1);
            item55.changeBrand(brand2);
            item56.changeBrand(brand2);
            item57.changeBrand(brand3);
            item58.changeBrand(brand3);
            item59.changeBrand(brand4);
            item60.changeBrand(brand4);
            /*코튼 팬츠 브랜드*/
            item61.changeBrand(brand0);
            item62.changeBrand(brand1);
            item63.changeBrand(brand2);
            item64.changeBrand(brand3);
            item65.changeBrand(brand4);
            /*슈트 팬츠/슬랙스 브랜드*/
            item66.changeBrand(brand0);
            item67.changeBrand(brand1);
            item68.changeBrand(brand2);
            item69.changeBrand(brand3);
            item70.changeBrand(brand4);
            /*숏 팬츠 브랜드*/
            item71.changeBrand(brand0);
            item72.changeBrand(brand1);
            item73.changeBrand(brand2);
            item74.changeBrand(brand3);
            item75.changeBrand(brand4);
            /*슈트/블레이저 자켓 브랜드*/
            item76.changeBrand(brand0);
            item77.changeBrand(brand1);
            item78.changeBrand(brand2);
            item79.changeBrand(brand3);
            item80.changeBrand(brand4);
        }
    }
}
