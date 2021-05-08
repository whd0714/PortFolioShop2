package portfolioshop.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.comment.Comment;
import portfolioshop.delivery.Delivery;
import portfolioshop.cart.Cart;
import portfolioshop.order.Order;
import portfolioshop.review.Review;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime joinAt;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Delivery> deliveries = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private Cart cart;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    public Member(String userId, String password, String username) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.joinAt = LocalDateTime.now();
    }

    public void changeBasket(Cart cart) {
        this.cart = cart;
    }
}
