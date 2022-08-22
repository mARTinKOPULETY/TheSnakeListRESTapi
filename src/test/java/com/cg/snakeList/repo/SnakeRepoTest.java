package com.cg.snakeList.repo;


import com.cg.snakeList.entity.Snake;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.List;
@DataJpaTest
class SnakeRepoTest {

    @Autowired
     private SnakeRepo testRepo;

// test funkce, ktera vyhleda v DB hada podle toho, zda-li obrazek obsahuje konkretni pismenko



/*otestovani funkce z repository s vlastni SQL query("SELECT * FROM snake WHERE name LIKE %:letter%")
    * toto query funguje v tomto testu. Oproti standardu ale chybi obaleni %:letter% do jednoduchych uvozovek
    * chova se jako kdyby  v query  byla funkce BINARY, i kdyz tam neni.*/
    @Test
    void canGetNameByLetterTEST() {
      /*  Arrange -  vytvoreni testovacich objektu snake, ulozeni do DB, vyloveni vsech snakes z DB,
         pro overeni.
         */
      String letter = "a";
      Snake snake1 = new Snake(1L, "Black Adder", "VVVV:<");
      Snake snake2 = new Snake(2L, "Mamba", "vvvvVVVV:<");
      Snake snake3 = new Snake(3L, "Adder", "vvvv:<");
        testRepo.save(snake1);
        testRepo.save(snake2);
        testRepo.save(snake3);
        List<Snake> original = testRepo.findAll();
        //Act- otestovani  funkce z repository
        List<Snake> expected = testRepo.getNameByLetterTEST(letter);
        //Assert
        System.out.println("Original" + original);//vypise vsechny
        System.out.println("expected" + expected);//vypise "Blask Adder" a "Mamba", podle predpokladu test failne, kdyz se zakomentuje vytvoreni snake3 a jeho ulozeni, tak test passne
        Assert.assertEquals(original, expected );

    }



    /* Uplne  stejny  test jako predesly a stejne  query ale s CONCAT() ("SELECT * FROM snake WHERE pic LIKE BINARY CONCAT('%',:letter,'%')")
     * toto query funguje pri testovani v Postmanu, ale pri testovani nefunguje. Vyhodi ecxeption:
     * WARN 7528 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 42000, SQLState: 42000,
     * org.springframework.dao.InvalidDataAccessResourceUsageException: could not prepare statement;
     * SQL [SELECT * FROM snake WHERE name LIKE BINARY CONCAT('%',?,'%')]; nested exception is
     * org.hibernate.exception.SQLGrammarException: could not prepare statement
     * Nerozumim tomu, vzdycky jsem bral Postman jako Svaty Gral a co rekne Postman to plati.
     * Ted jsem ve schyzofreni situaci  A) bud mi app funguje, ale nejde mi otestovat, nebo B) nejde mi otestovat ,ale funguje mi*/

    @Test
    void canGetNameByLetterPOSTMAN() {
      /*  Arrange -  vytvoreni testovacich objektu snake, ulozeni do DB, vyloveni vsech snakes z DB,
         pro overeni.
         */
        String letter = "a";
        Snake snake1 = new Snake(1L, "Black Adder", "VVVV:<");
        Snake snake2 = new Snake(2L, "Mamba", "vvvvVVVV:<");
        Snake snake3 = new Snake(3L, "Adder", "vvvv:<");
        testRepo.save(snake1);
        testRepo.save(snake2);
        testRepo.save(snake3);
        List<Snake> original = testRepo.findAll();
        //Act- otestovani  funkce z repository
        List<Snake> expected = testRepo.getNameByLetterPOSTMAN(letter);
        //Assert
        System.out.println("Original" + original);
        System.out.println("expected" + expected);
        Assert.assertEquals(original, expected );
    }


}