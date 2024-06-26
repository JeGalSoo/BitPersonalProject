package com.turing.api.article.repository;


import com.turing.api.article.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>, ItemDao{
    List<Item> findByOrderByVolume();

    @Query(value = "select i from items i where i.item=:search and i.date between :state and :date")
    List<Item> findDetail1(@Param("search") String search, @Param("state") Date sdate, @Param("date") Date edate);

//    // JPQL Default방식
//    @Query("select a from articles a where a.board.id = :boardId")
//    public List<Article> getArticlesByBoardId(@Param("boardId") Long boardId);
//
//
//    //Native방식
//    @Query(value = "select * from articles a where a.board.id = :boardId", nativeQuery = true)
//    List<Article> getArticlesByBoardId2(@Param("boardId") Long boardId);
//    @Query(value = "SELECT * from articles a where a.board.id = :boardId", nativeQuery = true)
//    List<Map<String,Object>> getReviewArticles(@Param("boardId") Long boardId);
//
//
////    String articleDtoMapping = "new com.example.demo.article.model.ArticleDto(a.id,a.title,a.content,a.writer.name, a.board.id,a.regDate,a.modDate)";
////    @Query("select "+ articleDtoMapping+" from articles a where a.board.id = :boardId")
////    List<Article> getArticleDtosByBoardId(@Param("boardId") Long boardId);
//
//
//    List<Article> findAllByBoardId(Long id);
//
//    List<Article> findAllByOrderByIdDesc();
}
