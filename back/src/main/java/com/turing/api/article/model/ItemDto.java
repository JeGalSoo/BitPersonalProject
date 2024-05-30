package com.turing.api.article.model;

import lombok.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class ItemDto {
    private Long id;
    private Float open;
    private Float high;
    private Float low;
    private Float close;
    private Float adjClose;
    private int volume;
    private Date date;
}
