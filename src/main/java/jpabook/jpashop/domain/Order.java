package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") // order db예약어이기 때문에 문제가 있을수있어서.
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
    private Long memberId;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
