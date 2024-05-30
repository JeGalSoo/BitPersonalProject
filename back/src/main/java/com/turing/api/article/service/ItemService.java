package com.turing.api.article.service;

import com.turing.api.article.model.Item;
import com.turing.api.article.model.ItemDto;

import java.util.List;
import java.util.Map;

public interface ItemService {

    default Item dtoToEntity(ItemDto dto) {
        return Item.builder()
                .id(dto.getId())
                .open(dto.getOpen())
                .high(dto.getHigh())
                .low(dto.getLow())
                .close(dto.getClose())
                .adjClose(dto.getAdjClose())
                .volume(dto.getVolume())
                .date(dto.getDate())
                .build();
    }

    default ItemDto entityToDto(Item ent) {
        return ItemDto.builder()
                .id(ent.getId())
                .open(ent.getOpen())
                .high(ent.getHigh())
                .low(ent.getLow())
                .close(ent.getClose())
                .adjClose(ent.getAdjClose())
                .volume(ent.getVolume())
                .date(ent.getDate())
                .build();
    }

    List<Item> findByVolume();

    List<Item> findAll();
}