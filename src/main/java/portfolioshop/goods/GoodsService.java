package portfolioshop.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.goods.enumType.SaleStatus;
import portfolioshop.item.Item;
import portfolioshop.productSetting.dto.DeleteOptionDto;
import portfolioshop.productSetting.dto.ProductUpdateDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public void createGoods(Item item, ProductUpdateDto productUpdateDto) {
        List<String> sizes = productUpdateDto.getSizes();
        List<Integer> counts = productUpdateDto.getCounts();
        List<SaleStatus> saleStatuses = productUpdateDto.getSaleStatuses();
        if(sizes!=null) {
            for(int i = 0; i < sizes.size(); i++) {
                Goods goods = new Goods(sizes.get(i), counts.get(i), saleStatuses.get(i));
                goods.changeItem(item);
                goodsRepository.save(goods);
            }
        }

    }

    public void updateGoods(Item item, ProductUpdateDto productUpdateDto) {
        List<String> sizes = productUpdateDto.getSizes2();
        List<Integer> counts = productUpdateDto.getCounts2();
        List<SaleStatus> saleStatuses = productUpdateDto.getSaleStatuses2();

        List<Goods> goods = item.getGoods();

        if(sizes.size() != 0) {

            for(int i = 0; i < sizes.size(); i++) {
                if(sizes.get(i) != null) {
                    goods.get(i).changeSize(sizes.get(i));
                }
            }
        }
        if(counts.size() != 0) {
            for(int i = 0; i < sizes.size(); i++) {
                if(counts.get(i) != null) {
                    goods.get(i).changeCount(counts.get(i));
                }
            }
        }

        if(saleStatuses.size() != 0){
            for(int i = 0; i < sizes.size(); i++) {
                if(saleStatuses.get(i) != null) {
                    goods.get(i).changeSaleStatuses(saleStatuses.get(i));
                }
            }
        }
    }

    public void deleteGoods(DeleteOptionDto deleteOptionDto) {

        List<Long> collect = Arrays.stream(deleteOptionDto.getPath()).collect(Collectors.toList());

        for (Long aLong : collect) {
            Optional<Goods> byId = goodsRepository.findById(aLong);
            byId.ifPresent(goods -> goodsRepository.delete(goods));
        }


    }
}
