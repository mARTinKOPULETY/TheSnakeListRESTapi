package com.cg.snakeList.repo;

import com.cg.snakeList.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnakeRepo extends JpaRepository<Snake, Long> {


//    query for finding list of snakes, which name contains concrete letter(s)
    @Query(value="SELECT * FROM snake WHERE snake_name LIKE BINARY CONCAT('%',:letter,'%')", nativeQuery = true)
    List<Snake> getNameByLetterPOSTMAN(@Param("letter") String letter);

    /*  //original query that works in  Workbench
        @Query(value="SELECT * FROM snake WHERE name LIKE BINARY '%:letter%'", nativeQuery = true)
        List<Snake> getNameByLetter(@Param("letter") String letter);*/

      //query that can find lower case, and upper case letters in @TEST- nevim proc to v @TESTu dokaze rozlisit jak velka, tak mala pismena bez funkce BINARY
      // a v Postmanu to uz nerozlisuje mezi velkymi a  malzmi pismeny
        @Query(value="SELECT * FROM snake WHERE snake_name LIKE %:letter%", nativeQuery = true)
        List<Snake> getNameByLetterTEST(@Param("letter") String letter);

    /*query for finding list of snakes, which picture contains concrete letter(s)*/
    @Query(value="SELECT * FROM snake WHERE pic LIKE BINARY CONCAT('%',:letter,'%')", nativeQuery = true)
    List<Snake> getPicByLetter(@Param("letter") String letter);


    List<Snake> findBySnakeNameIs(String snakeName);
    List<Snake> findBySnakePicIs(String snakePic);
    List<User> findByUserUserIdIs(Long userId);
}
