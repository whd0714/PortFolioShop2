package portfolioshop.item;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import portfolioshop.brand.Brand;
import portfolioshop.category.QCategory;
import portfolioshop.goods.QGoods;
import portfolioshop.item.dto.queryDto.GoodsCategoryListSearchCondition;
import portfolioshop.item.dto.queryDto.ItemSettingSearchCondition;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.item.searchQuery.SettingMainCondition;
import portfolioshop.itemCategory.QItemCategory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static portfolioshop.brand.QBrand.brand;
import static portfolioshop.category.QCategory.category;
import static portfolioshop.goods.QGoods.goods;
import static portfolioshop.item.QItem.item;
import static portfolioshop.itemCategory.QItemCategory.itemCategory;

public class ItemRepositoryImpl implements ItemSearchRepository{

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /*@Override
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
                .join(item.itemCategories, itemCategory)
                .join(itemCategory.category, category)
                .where(categoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifier)
                .fetchResults();

      /*  QueryResults<Item> results;
        if (condition.getSort() != null) {
            if(condition.getSort().equals("신상품순")) {
                results = queryFactory
                        .selectFrom(item)
                        .join(item.brand, brand).fetchJoin()
                        .join(item.itemCategories, itemCategory)
                        .join(itemCategory.category, category)
                        .where(categoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(orderSpecifier)
                        .fetchResults();
            }else if(condition.getSort().equals("낮은가격순")) {
                results = queryFactory
                        .selectFrom(item)
                        .join(item.brand, brand).fetchJoin()
                        .join(item.itemCategories, itemCategory)
                        .join(itemCategory.category, category)
                        .where(categoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(item.itemPrice.asc())
                        .fetchResults();
            } else {
                results = queryFactory
                        .selectFrom(item)
                        .join(item.brand, brand).fetchJoin()
                        .join(item.itemCategories, itemCategory)
                        .join(itemCategory.category, category)
                        .where(categoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(item.itemPrice.desc())
                        .fetchResults();
            }
        } else {
            results = queryFactory
                    .selectFrom(item)
                    .join(item.brand, brand).fetchJoin()
                    .join(item.itemCategories, itemCategory)
                    .join(itemCategory.category, category)
                    .where(categoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
        }
*/

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
                .join(item.itemCategories, itemCategory)
                .join(itemCategory.category, category)
                .where(mainCategoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifier)
                .fetchResults();


       /* QueryResults<Item> results;
        if (condition.getSort() != null) {
            if(condition.getSort().equals("신상품순")) {
                results =
            }else if(condition.getSort().equals("낮은가격순")) {
                results = queryFactory
                        .selectFrom(item)
                        .join(item.brand, brand).fetchJoin()
                        .join(item.itemCategories, itemCategory)
                        .join(itemCategory.category, category)
                        .where(mainCategoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(item.itemPrice.asc())
                        .fetchResults();
            } else {
                results = queryFactory
                        .selectFrom(item)
                        .join(item.brand, brand).fetchJoin()
                        .join(item.itemCategories, itemCategory)
                        .join(itemCategory.category, category)
                        .where(mainCategoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(item.itemPrice.desc())
                        .fetchResults();
            }
        } else {
            results = queryFactory
                    .selectFrom(item)
                    .join(item.brand, brand).fetchJoin()
                    .join(item.itemCategories, itemCategory)
                    .join(itemCategory.category, category)
                    .where(mainCategoryIdEq(categoryId), brandNameEq(condition.getBrandName()))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
        }*/


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
        return categoryId != null ? category.parent.id.eq(categoryId) : null;
    }

    @Override
    public List<Item> findItemFetchJoinNoConditions(Long categoryId) {
        List<Item> fetch = queryFactory
                .select(item)
                .from(item)
                .join(item.brand, brand).fetchJoin()
                .join(item.itemCategories, itemCategory)
                .join(itemCategory.category, category)
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
                .join(item.itemCategories, itemCategory)
                .join(itemCategory.category, category)
                .where(mainCategoryIdEq(categoryId))
                .fetch();

        return fetch;
    }

    private BooleanExpression categoryIdEq(Long categoryId) {

        return categoryId != null ? category.id.eq(categoryId) : null;
    }


    private BooleanExpression itemNameEq(String itemName) {
        return hasText(itemName) ? item.itemName.contains(itemName) : null;

    }

    private BooleanExpression brandNameEq(String brandName) {
        return hasText(brandName) ? brand.brandName.contains(brandName) : null;
    }


}
