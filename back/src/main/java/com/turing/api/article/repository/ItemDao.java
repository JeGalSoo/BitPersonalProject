package com.turing.api.article.repository;

import com.querydsl.core.Tuple;
import com.turing.api.article.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemDao {
    List<Item> findDetail(String search, Date sdate, Date edate);

}
