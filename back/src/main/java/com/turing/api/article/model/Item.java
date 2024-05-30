package com.turing.api.article.model;

import com.turing.api.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
@ToString(exclude = {"id"})
@Entity(name = "items")
public class Item extends BaseEntity {
    @Id
    @Column(name = "item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    @NumberFormat(pattern = "###,###")
    private Float open;
    private Float high;
    private Float low;
    private Float close;
    private Float adjClose;
    private int volume;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Setter
    private String formattedNumber;

}