package portfolioshop.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.delivery.dto.newDeliveryDto;
import portfolioshop.member.Member;
import portfolioshop.member.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryService {

    private final MemberRepository memberRepository;
    private final DeliveryRepository deliveryRepository;

    public void createNewDelivery(newDeliveryDto newDeliveryDto, Long memberId) {
        String phone = newDeliveryDto.getNum1() + "-" + newDeliveryDto.getNum2() + "-" + newDeliveryDto.getNum3();
        System.out.println("!!!!!!!!!");
        Optional<Member> byId = memberRepository.findById(memberId);

        byId.ifPresent(member -> {
            Delivery delivery = new Delivery(newDeliveryDto.getDeliveryName(), phone, newDeliveryDto.getZipCode(), newDeliveryDto.getAddress(),
                    newDeliveryDto.getDetailAddress(), member);
            deliveryRepository.save(delivery);
        });

        List<Delivery> all = deliveryRepository.findAll();
        System.out.println("!!!!!!!!!" + all);
    }
}
