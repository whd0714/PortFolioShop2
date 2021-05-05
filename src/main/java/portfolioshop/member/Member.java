package portfolioshop.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.delivery.Delivery;
import portfolioshop.cart.Cart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String userId;
    private String username;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Delivery> deliveries = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private Cart cart;

    public Member(String userId, String password, String username) {
        this.userId = userId;
        this.password = password;
        this.username = username;
    }

    public void changeBasket(Cart cart) {
        this.cart = cart;
    }
}
