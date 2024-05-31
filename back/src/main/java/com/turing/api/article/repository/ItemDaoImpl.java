package com.turing.api.article.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.turing.api.article.model.Item;
import com.turing.api.article.model.ItemDto;
import com.turing.api.article.model.QItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ItemDaoImpl implements ItemDao{
    private final JPAQueryFactory factory;
    private final QItem item = QItem.item1;


    @Override
    public List<Item> findDetail(String search, Date sdate, Date edate) {
        return factory
                .selectFrom(item)
                .where(item.item.eq(search))
                .where(item.date.between(sdate,edate))
                .fetch();
    }
}
