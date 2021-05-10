package portfolioshop.item;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import portfolioshop.item.dto.queryDto.GoodsCategoryListSearchCondition;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.item.searchQuery.SettingMainCondition;
import portfolioshop.main.dto.MainSearchDto;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static portfolioshop.brand.QBrand.brand;
import static portfolioshop.category.QCategory.category;
import static portfolioshop.item.QItem.item;
import static portfolioshop.itemCategory.QItemCategory.itemCategory;
import static portfolioshop.itemTag.QItemTag.itemTag;

public class ItemRepositoryImpl implements ItemSearchRepository{

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

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

    @Override
    public List<Item> findNewItemByCategory() {
        queryFactory
                .selectFrom(item)
                .join(item.brand, brand).fetchJoin()
                .offset(0)
                .limit(10)
                .fetchResults();
        return null;
    }

    @Override
    public Page<Item> findItemFetchJoin(GoodsCategoryListSearchCondition condition, Long categoryId, Pageable pageable) {

        OrderSpecifier orderSpecifier = getOrderSpecifier(condition);

        QueryResults<Item> results = queryFactory
                .selectFrom(item)
                .join(item.brand, brand).fetchJoin()
                .where(categoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifier)
                .fetchResults();

        List<Item> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public Page<Item> findItemFetchJoin2(GoodsCategoryListSearchCondition condition, Long categoryId, Pageable pageable) {

        OrderSpecifier orderSpecifier = getOrderSpecifier(condition);

        QueryResults<Item> results = queryFactory
                .selectFrom(item)
                .join(item.brand, brand).fetchJoin()
                .where(mainCategoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifier)
                .fetchResults();

        List<Item> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    private OrderSpecifier getOrderSpecifier(GoodsCategoryListSearchCondition condition) {
        OrderSpecifier orderSpecifier;
        if (condition.getSort() != null) {
            if (condition.getSort().equals("신상품순")) {
                orderSpecifier = new OrderSpecifier(Order.ASC, item.createItemTime);
            } else if (condition.getSort().equals("낮은가격순")) {
                orderSpecifier = new OrderSpecifier(Order.ASC, item.itemPrice);
            } else {
                orderSpecifier = new OrderSpecifier(Order.DESC, item.itemPrice);
            }
        } else {
            orderSpecifier = new OrderSpecifier(Order.ASC, item.id);
        }
        return orderSpecifier;
    }

    private BooleanExpression mainCategoryIdEq(Long categoryId) {
        return categoryId != null ? item.itemCategories.any().category.parent.id.eq(categoryId) : null;
    }

    @Override
    public List<Item> findItemFetchJoinNoConditions(Long categoryId) {
        List<Item> fetch = queryFactory
                .select(item)
                .from(item)
                .join(item.brand, brand).fetchJoin()
                .where(categoryIdEq(categoryId))
                .fetch();

        return fetch;
    }

    @Override
    public List<Item> findItemFetchJoinNoConditions2(Long categoryId) {
        List<Item> fetch = queryFactory
                .select(item)
                .from(item)
                .join(item.brand, brand).fetchJoin()
                .where(mainCategoryIdEq(categoryId))
                .fetch();

        return fetch;
    }

    @Override
    public Page<Item> findItemFromQuery(MainSearchDto mainSearchDto, Pageable pageable) {

        QueryResults<Item> results;
        results = queryFactory
                .selectFrom(item).distinct()
                .join(item.brand, brand).fetchJoin()
                .where(itemNameOrBrandNameOrCategoryNameEq(mainSearchDto.getQuery()),tagNameEq(mainSearchDto.getTagName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public List<Item> findItemFromBestView() {
        List<Item> fetch = queryFactory
                .selectFrom(item)
                .orderBy(item.view.desc())
                .limit(10)
                .fetch();

        return fetch;
    }

    @Override
    public List<Item> findItemFromBestSale() {
        List<Item> fetch = queryFactory
                .selectFrom(item)
                .orderBy(item.saleRate.desc())
                .limit(10)
                .fetch();
        return fetch;
    }

    @Override
    public List<Item> findItemFromNewItem() {
        List<Item> fetch = queryFactory
                .selectFrom(item)
                .orderBy(item.createItemTime.asc())
                .limit(10)
                .fetch();
        return fetch;
    }


    private BooleanExpression tagNameEq(String tagName) {
        return hasText(tagName) ? item.itemTags.any().tag.tagName.eq(tagName): null;
    }

    private BooleanExpression categoryIdEq(Long categoryId) {

        return categoryId != null ? item.itemCategories.any().category.id.eq(categoryId) : null;
    }
    private BooleanExpression itemNameOrBrandNameOrCategoryNameEq(String query) {
        return hasText(query) ? item.itemName.contains(query).or(brand.brandName.contains(query)
                .or(item.itemCategories.any().category.name.contains(query))) : null;
    }

    private BooleanExpression itemNameEq(String itemName) {
        return hasText(itemName) ? item.itemName.contains(itemName) : null;

    }

    private BooleanExpression brandNameEq(String brandName) {
        return hasText(brandName) ? brand.brandName.contains(brandName) : null;
    }

}
