package com.example.gdsc.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    //join 중복
//    @OneToOne
//    private ProductDetail productDetail;

    //중복 제거
    @OneToOne(mappedBy = "product")
    @ToString.Exclude //순환참조 방지
    private ProductDetail productDetail;

    //다대일 매핑
    @ManyToOne
    @JoinColumn(name="provider_id")
    @ToString.Exclude
    private Provider provider;

}
