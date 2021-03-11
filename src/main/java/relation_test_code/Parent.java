package relation_test_code;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
                                                                                     //cascade => list에 추가되는 노드는 자동으로 영속성 관리됨(parent에서 영속성객체에 속할때).
                                                                                     //의존관계주인 개념과는 별개의 개념임.
                                                                                     //orphanReomoval이 켜져있지 않으면 parent가 영속성 객체에서 빠지기 전까지는 child의 노드가
                                                                                     //삭제되더라도 child를 테이블에서 삭제하지않음.(실제 child객체에서 parent를 빼기전까지..->의존성 주인)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemoval => list에서 하나의 노드를 뺄때 해당 노드를 테이블에서 삭제.
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

    public List<Child> getList() {
        return childList;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
