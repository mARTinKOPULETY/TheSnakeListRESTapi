package com.cg.snakeList.service;

import com.cg.snakeList.entity.Snake;
import com.cg.snakeList.exceptions.IllegalnputException;
import com.cg.snakeList.repo.SnakeRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SnakeService {

    private final SnakeRepo snakeRepo;

    public SnakeService(SnakeRepo snakeRepo) {
        this.snakeRepo = snakeRepo;
    }


    /*lists all snakes*/
    public List<Snake> getAllSnakes() {
        return snakeRepo.findAll();
    }

    /*make new snake*/
    public void addSnake(Snake snake) {
        /*checks if there is  a name of a snake*/
        if (snake.getName().isBlank()){
            throw  new IllegalnputException("Insert a NAME of a snake please!");
        }
        /*checks if there is a pic of a snake*/
         else  if (snake.getPic().isBlank()){
            throw new IllegalnputException("Insert a PICTURE of a snake please!");
        }
        /*checks if head is placed into the picture of snake*/
         else  if (!snake.getPic().endsWith(":<")){
            throw new IllegalnputException("Poor snake, you made it without a head!");
        }
         else {
             snakeRepo.save(snake);// snake value is what we want  to capture in Test
         }
    }

    /*lists snake if snakes name contains concrete letter
    * throw exception if there is no required snake
    * */
    public List<Snake> getNameByLetterTEST(String letter) {
        List<Snake> existLetter = snakeRepo.getNameByLetterTEST(letter);
        if (existLetter.isEmpty()) {
            throw new IllegalStateException("There is no snake contains letter \"" + letter + "\".");
        }
        return snakeRepo.getNameByLetterTEST(letter);
    }
    public List<Snake> getNameByLetterPOSTMAN(String letter) {
        List<Snake> existLetter = snakeRepo.getNameByLetterPOSTMAN(letter);
        if (existLetter.isEmpty()) {
            throw new IllegalStateException("There is no snake contains letter \"" + letter + "\".");
        }
        return snakeRepo.getNameByLetterPOSTMAN(letter);
    }

    /*lists snake if snakes picture contains concrete letter
     * throw exception if there is no required snake
     * */
    public List<Snake> getNameByPic(String letter) {
        List<Snake> existsLetter = snakeRepo.getPicByLetter(letter);
        if (existsLetter.isEmpty()) {
            throw new IllegalStateException("There is no snake's picture contains letter \"" + letter + "\".");
        }
        return snakeRepo.getPicByLetter(letter);
    }

    /*updates snake by id*/
    @Transactional
    public void update(Long id,
                       String name,
                       String pic) {
        Snake snake = snakeRepo.findById(id).orElseThrow(() -> new IllegalStateException("There is not a snake with id " + id + "."));
        /*checks if there is  a name entered that should be updated*/
        if ( !name.isBlank() && !Objects.equals(snake.getName(), name)) {
            snake.setName(name);
        } else {
            throw new IllegalStateException("Insert a NAME of a snake please!");
        }
        /*checks if there is  a pic entered that should be updated*/
        if (!pic.isBlank() && !Objects.equals(snake.getPic(), pic)) {
            /*check if there is a head entered in picture*/
            if (!pic.endsWith(":<")){
                throw new IllegalStateException("Poor snake, you made it without a head!");
            }
            snake.setPic(pic);
        }else {
            throw new IllegalStateException("Insert a PICTURE of a snake please!");
        }
    }

    /*deletes a snake by id*/
    public void deleteSnake(Long id) { snakeRepo.deleteById(id);

    }
}
