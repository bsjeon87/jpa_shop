package relation_test_code;

import javax.persistence.*;

//@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member memeber;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
