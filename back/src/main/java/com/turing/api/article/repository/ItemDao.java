package com.turing.api.article.repository;

import com.querydsl.core.Tuple;
import com.turing.api.article.model.Item;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public interface ItemDao {
    List<Item> findDetail(String search, Date sdate, Date edate);

}
