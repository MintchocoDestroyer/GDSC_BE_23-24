package gdsc.hello.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name="producer")
public class Producer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;

    @ManyToMany
    @JoinTable(name="midTable")
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }
}
