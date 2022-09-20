package com.cg.snakeList.service;


import com.cg.snakeList.*;
import com.cg.snakeList.entity.*;
import com.cg.snakeList.exceptions.IllegalInputException;
import com.cg.snakeList.repo.SnakeRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.cg.snakeList.service.UserService.idFromUser;

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
        List<Snake> isThereSnakeName = snakeRepo.findBySnakeNameIs(snake.getSnakeName());
        List<Snake> isThereSnakePic = snakeRepo.findBySnakePicIs(snake.getSnakePic());
        int sizeNameList = isThereSnakeName.size();
        int sizePicList = isThereSnakePic.size();

        snake.setUserId(idFromUser);
        /*checks if  user is  logged*/
      if (snake.getUserId() == null) {
            throw new IllegalInputException("You are not logged, you need log first!");
//            checks if there is  a name of a snake
        } else if (snake.getSnakeName().isBlank()) {
            throw new IllegalInputException("Insert a NAME of a snake please!");
        } else if (sizeNameList != 0) {
            throw new IllegalInputException("The " + snake.getSnakeName() + " snake is alredy in database!");
        }
        /*checks if there is a pic of a snake*/
        else if (snake.getSnakePic().isBlank()) {
            throw new IllegalInputException("Insert a PICTURE of a snake please!");
        } else if (sizePicList != 0) {
            throw new IllegalInputException("The picture " + snake.getSnakePic() + " what you inserted is in the database yet.");
        }
        /*checks if head is placed into the picture of snake*/
        else if (!snake.getSnakePic().endsWith(":<")) {
            throw new IllegalInputException("Poor snake, you made it without a head!");
        } else {
            snakeRepo.save(snake);// snake value is what we want  to capture in Test
        }
    }

    /*lists snake if snakes name contains concrete letter*/
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

    /*lists snake if snakes picture contains concrete letter*/
    public List<Snake> getNameByPic(String letter) {
        List<Snake> existsLetter = snakeRepo.getPicByLetter(letter);
        if (existsLetter.isEmpty()) {
            throw new IllegalStateException("There is no snake's picture contains letter \"" + letter + "\".");
        }
        return snakeRepo.getPicByLetter(letter);
    }

    /*updates snake by id*/
    @Transactional
    public void updateSnake(Long snakeId, String snakeName, String snakePic) {
        //checks if there is snake to update by id
        Snake snake = snakeRepo.findById(snakeId).orElseThrow(() -> new IllegalInputException("There is not a snake with snakeId " + snakeId + "."));
        Long userId= snake.getUserId();
        if(snake.getUserId() != idFromUser){
            throw new IllegalInputException("You cant edit this snake!");
        }
        /*checks if there is  a snakeName entered that should be updated*/
        if (!snakeName.isBlank() && !snakeName.equals(snake.getSnakeName())) {
            snake.setSnakeName(snakeName);
        } else if (snakeName.equals(snake.getSnakeName())) {
            throw new IllegalInputException("The " + snakeName + " is already in the database, try make another snakeName!");
        } else {
            throw new IllegalInputException("Insert a NAME of a snake please!");
        }
        /*checks if there is a pic entered that should be updated*/
        if (!snakePic.isBlank() && !snakePic.equals(snake.getSnakePic())) {
            /*check if there is a head entered in picture*/
            if (!snakePic.endsWith(":<")) {
                throw new IllegalInputException("Poor snake, you made it without a head!");
            }
            snake.setSnakePic(snakePic);
        } else if (snakePic.equals(snake.getSnakePic())) {
            throw new IllegalInputException("There is already exist snake with this picture: " + snakePic);
        } else {

            throw new IllegalInputException("Insert a PICTURE of a snake please!");
        }
    }



    public void deleteSnake(Long snakeId, Snake snake) {
        snakeRepo.findById(snakeId).orElseThrow(() -> new IllegalInputException("There is not a snake with id " + snakeId + "."));
        if(snake.getUserId() != idFromUser){
            throw new IllegalInputException("You cant delete this snake!");
        }
        snakeRepo.deleteById(snakeId);

    }

    public void deleteAllSnakes() {
        snakeRepo.deleteAll();
    }
}