package com.example.gdsc.data.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "provider")
public class Provider extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //다대일 양방향 매핑
    @OneToMany(mappedBy = "provider",fetch = FetchType.EAGER)//즉시로딩 설정
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();
}
