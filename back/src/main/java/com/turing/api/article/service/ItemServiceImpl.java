package com.turing.api.article.service;

import com.turing.api.article.model.Item;
import com.turing.api.article.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository re;

    @Override
    public List<Item> findByVolume() {
        return re.findByOrderByVolume();
    }

    @Override
    public List<Item> findAll() {
        return re.findAll();
    }


//    @Override
//    public Messenger save(ItemDto article) {
//        re.save(dtoToEntity(article));
//        System.out.printf("123123123123"+article.toString());
//        return Messenger.builder()
//                .message("SUCCESS")
//                .id(article.getBoardId())
//                .build();
//    }
//
//    @Override
//    public Messenger deleteById(Long id) {
//        re.deleteById(id);
//        String message = re.findById(id).isEmpty()? "SUCCESS" : "FAIL";
//        return Messenger.builder()
//                .message(message)
//                .build();
//    }
//
//    @Override
//    public Messenger modify(ItemDto article) {
//        return new Messenger();
//    }
//
//    @Override
//    public List<ItemDto> findAll() {
//        return re.findAllByOrderByIdDesc().stream().map(i->entityToDto(i)).toList();
//    }
//
//    @Override
//    public Optional<ItemDto> findById(Long id) {
//        ItemDto dto = entityToDto(Objects.requireNonNull(re.findById(id).orElse(null)));
//        return Optional.ofNullable(dto);
//    }
//
//    @Override
//    public long count() {
//        return re.count();
//    }
//
//    @Override
//    public boolean existsById(Long id) {
//        return re.existsById(id);
//    }
//
//    @Override
//    public List<ItemDto> findAllByBoardId(Long id) {
//        return re.findAllByOrderByIdDesc().stream().map(i->entityToDto(i)).toList();
//    }
}
