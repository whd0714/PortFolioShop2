package portfolioshop.item;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import portfolioshop.goods.QGoods;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.item.searchQuery.SettingMainCondition;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static portfolioshop.brand.QBrand.brand;
import static portfolioshop.goods.QGoods.goods;
import static portfolioshop.item.QItem.item;

public class ItemRepositoryImpl implements ItemSearchRepository{

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

/*    @Override
    public Page<Item> search(ItemSettingSearchCondition condition, Pageable pageable) {
        List<Item> contents = queryFactory
                .selectFrom(item)
                .leftJoin(item.brand, brand)
                .where(
                        itemNameEq(condition.getItemName()),
                        brandNameEq(condition.getBrandName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = queryFactory
                .selectFrom(item)
                .where(
                        itemNameEq(condition.getItemName()),
                        brandNameEq(condition.getBrandName()))
                .fetchCount();

        return new PageImpl<>(contents, pageable, count);
    }*/

    @Override
    public Page<Item> searchProductSetting(SettingMainCondition condition, Pageable pageable) {
        QueryResults<Item> results = queryFactory
                .selectFrom(item)
                .join(item.brand, brand).fetchJoin()
                .where(
                        itemNameEq(condition.getItemName()),
                        brandNameEq(condition.getBrandName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public List<Item> searchProductSetting2(SettingMainCondition condition) {
        List<Item> list = queryFactory
                .selectFrom(item)
                .join(item.brand, brand).fetchJoin()
                .where(itemNameEq(condition.getItemName()), brandNameEq(condition.getBrandName()))
                .fetch();
        return list;
    }

    @Override
    public Page<Item> findAllByPage(Pageable pageable) {
        QueryResults<Item> results = queryFactory
                .selectFrom(item)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> contents = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public Page<Item> findAllItemByCategory(ItemCategoryCondition condition, Pageable pageable) {
        QueryResults<Item> results = queryFactory.selectFrom(item)
                .join(item.brand.brand).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public Page<Item> findAllItemInSettingForm(Pageable pageable) {
        QueryResults<Item> results = queryFactory
                .selectFrom(item)
                .join(item.brand, brand).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    private Predicate itemNameEq(String itemName) {
        return hasText(itemName) ? item.itemName.contains(itemName) : null;

    }

    private Predicate brandNameEq(String brandName) {
        return hasText(brandName) ? brand.brandName.contains(brandName) : null;
    }
}
