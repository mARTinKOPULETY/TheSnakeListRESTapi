package com.cg.snakeList.service;

import com.cg.snakeList.controller.*;
import com.cg.snakeList.entity.Snake;
import com.cg.snakeList.exceptions.IllegalInputException;
import com.cg.snakeList.repo.SnakeRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestService {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Mock
    private SnakeRepo snakeRepoMock;
    @InjectMocks
    private SnakeService underTest;
    //given
    private Snake snake = new Snake(1L, "Adder", "vvv:<", 1L);




    @BeforeTest
    public void init() {
        MockitoAnnotations.openMocks(this);
        underTest = new SnakeService(snakeRepoMock);
    }
    @BeforeMethod
    void beforeMethod(){
        //given
        when(snakeRepoMock.findById(snake.getSnakeId())).thenReturn(Optional.of(snake));
    }
////////////////////////CREATE\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//TODO
     @Test
    void canAddSnake() {
        //when

        logger.error("Value of userId: "+ snake.getUserId()+ " IdFromUser:\n Snakes name: "+ snake.getSnakeName());

        underTest.addSnake(snake);
        //then
            /*ArgumentCaptor allows us to capture an argument passed to a method to inspect it.
            checks if repository was invoked with the same snakethat is  passed in addSnake(){snakeRepo.save(snake)*/
        ArgumentCaptor<Snake> snakeArgumentCaptor = ArgumentCaptor.forClass(Snake.class);
        /* to verify if repo was called with save. snakeRepo is Mock,
         * we want  to capture the  actual student that was passed inside .save method*/
        verify(snakeRepoMock).save(snakeArgumentCaptor.capture());
        /*capturedSnake is what service method recieves -> snake obj*/
        Snake capturedSnake = snakeArgumentCaptor.getValue();

        Assert.assertTrue(capturedSnake.equals(snake));

    }

    @Test(expectedExceptions = IllegalInputException.class)
    void canNotCreateNoNameInput(){
        snake.setSnakeName("");
        //when
        underTest.addSnake(snake);
    }

/////////////////////////READ\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Test
    void canGetAll(){
        //given
        List<Snake> snakes = new ArrayList();
        snakes.add(snake);
        snakes.add(new Snake(2L, "Black Adder", "VVVV:<", 1L));
        when(snakeRepoMock.findAll()).thenReturn(snakes);
        //when
        List<Snake> expected = underTest.getAllSnakes();
        //then
        Assert.assertEquals(snakes, expected);
    }
////////////////////////UPDATE\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Test
    void canUpdateName(){
        //when
        underTest.updateSnake(1L, "Cobra","vvvvVVVVvvvv(8):<");
        //then
        Assert.assertEquals(snake.getSnakeName(),"Cobra");
    }

    @Test
    void canUpdatePic(){
        //when
        underTest.updateSnake(1L, "snail","__@_:<");
        //then
        Assert.assertEquals(snake.getSnakePic(),"__@_:<");
    }

    @Test(expectedExceptions = IllegalInputException.class)
    void canNotUpdateNameFindByIdFail(){
       //when
       underTest.updateSnake(2L, "Mamba","WWW:<");
   }

   @Test(expectedExceptions = IllegalInputException.class)
    void canNotUpdateNameNoInput(){
       //when
       underTest.updateSnake(1L, "","WWW:<");
   }

   @Test(expectedExceptions = IllegalInputException.class)
    void canNotUpdateNameIsAlreadyInTheDatabase(){
       //when
       underTest.updateSnake(1L, "Adder","WWW:<");
   }

   @Test(expectedExceptions = IllegalInputException.class)
    void canNotUpdatePicNoInput(){
       //when
       underTest.updateSnake(1L, "Mamba","");
   }

   @Test(expectedExceptions = IllegalInputException.class)
    void canNotUpdatePicNoHeadInput(){
       //when
       underTest.updateSnake(1L, "Mamba","WWW");
   }

   @Test(expectedExceptions = IllegalInputException.class)
    void canNotUpdatePicIsAlreadyInTheDatabase(){
       //when
       underTest.updateSnake(1L, "Mamba","vvv:<");
   }

////////////////////////DELETE\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Test
    void canDelete(){
        underTest.deleteSnake(1l, snake);
        verify(snakeRepoMock).deleteById(1l);
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void canNotDeleteById() {
        given(snakeRepoMock.findById(anyLong())).willReturn(Optional.ofNullable(null));
        underTest.deleteSnake(snake.getSnakeId(), snake);
        Assert.assertEquals(snake, null);

    }




}
