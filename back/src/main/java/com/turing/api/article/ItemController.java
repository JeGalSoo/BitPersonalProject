package com.turing.api.article;

import com.turing.api.article.model.Item;
import com.turing.api.article.model.ItemDto;
import com.turing.api.article.service.ItemService;
import com.turing.api.common.component.pagination.PageRequestVo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
})
@RequiredArgsConstructor
@RequestMapping(path="/api/item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {
    private final ItemService service;

    @GetMapping(path = "/findByVolume")
    public ResponseEntity<List<Item>> findByVolume() {
        return ResponseEntity.ok(service.findByVolume());
    }

    @GetMapping("/list")
    public List<Item> findAll(PageRequestVo vo){
        log.info("findAll request : {}", vo);
        return service.findAll();
    }

    @PostMapping( path = "/detail")
    public ResponseEntity<List<Item>> findAllByBoarId(@RequestBody Map<String,String> search, Date sdate, Date edate) throws ParseException {
        log.info(search.toString());
        return ResponseEntity.ok(service.findDetail(search));
    }
//    @PostMapping(path = "/save")
//        public ResponseEntity<Messenger> save(@RequestBody ItemDto article){
//        return ResponseEntity.ok(service.save(article));
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<Messenger> deleteById(@RequestParam Long id){
//        return ResponseEntity.ok(service.deleteById(id));
//    }
//
//    @PostMapping("/modify")
//    public ResponseEntity<Messenger> modify(@RequestBody ItemDto article){
//        return ResponseEntity.ok(service.modify(article));
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<List<ItemDto>> findAll(PageRequestVo vo){
//        log.info("findAll request : {}", vo);
//        return ResponseEntity.ok(service.findAll());
//    }
//

//
//    @GetMapping("")
//    public ResponseEntity<Long> count(){
//        return ResponseEntity.ok(service.count());
//    }
//
//    @PostMapping(path = "/check-id")
//    public ResponseEntity<Boolean> existsById(@RequestParam Long id){
//        return ResponseEntity.ok(service.existsById(id));
//    }

}
