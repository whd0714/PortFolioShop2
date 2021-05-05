package portfolioshop.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.member.Member;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String deliveryName;
    private String phone;
    private String zipCode;
    private String address;
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Delivery(String deliveryName, String phone, String zipCode, String address, String detailAddress, Member member) {
        this.deliveryName = deliveryName;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
        this.detailAddress = detailAddress;
        changeMember(member);
    }

    private void changeMember(Member member) {
        this.member = member;
        member.getDeliveries().add(this);
    }
}
