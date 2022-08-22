package com.cg.snakeList.service;

//import org.testng.annotations.Test;

import com.cg.snakeList.entity.Snake;
import com.cg.snakeList.exceptions.IllegalnputException;
import com.cg.snakeList.repo.SnakeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
/*right click to green arrow-> run coverage - shows which parts of method is tested*/
class SnakeServiceTest {
    @Mock
    private SnakeRepo snakeRepo;
  /*  //is no needed if @ExtendWith(MockitoExtension.class)
  private AutoCloseable autoCloseable;*/
    private SnakeService underTest;
    @BeforeEach
    void setUp() {
     /*//is no needed if @ExtendWith(MockitoExtension.class)
      autoCloseable = MockitoAnnotations.openMocks(this);*/
        underTest = new SnakeService(snakeRepo);
    }


    /*  //is no needed if @ExtendWith(MockitoExtension.class)
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }*/
    @Test
    void canGetSnakes() {
        //arrange
        //act
         underTest.getAllSnakes();
        //assertion from Mockito
        verify(snakeRepo).findAll();
    }
    @Test
    void canAddSnake() {
        //arrange
        Snake snake1 = new Snake(1L, "Adder", "vvvv:<");
        //act
        underTest.addSnake(snake1);
        //assertion
        /*ArgumentCaptor allows us to capture an argument passed to a method to inspect it.
        checks if repository was invoked with the same snakethat is  passed in addSnake(){snakeRepo.save(snake)*/
        ArgumentCaptor<Snake> snakeArgumentCaptor =
                ArgumentCaptor.forClass(Snake.class);
        /* to verify if repo was called with save. snakeRepo is Mock,
        * we want  to capture the  actual student that was passed inside .save method*/
        verify(snakeRepo).save(snakeArgumentCaptor.capture());
        /*capturedSnake is what service method recieves -> snake obj*/
        Snake capturedSnake = snakeArgumentCaptor.getValue();
        /*AssertionsForClassTypes.assertThat(org.assertj.core.api*/
        assertThat(capturedSnake).isEqualTo(snake1);
    }


    @Test
    void exceptionTestInvalidNameInput() {
        Snake snake = new Snake(1L, "", "vvvv:<");
        Exception exception = assertThrows(IllegalnputException.class, () -> underTest.addSnake(snake));
        assertEquals("Insert a NAME of a snake please!", exception.getMessage());
    }

    @Test
    void exceptionTestInvalidPicInput() {
        Snake snake = new Snake(1L, "Adder", "");
        Exception exception = assertThrows(IllegalnputException.class, () -> underTest.addSnake(snake));
        assertEquals("Insert a PICTURE of a snake please!", exception.getMessage());
    }
    @Test
    void exceptionTestInvalidPicWithNoHeadInput() {
        Snake snake = new Snake(1L, "Adder", "www");
        Exception exception = assertThrows(IllegalnputException.class, () -> underTest.addSnake(snake));
        assertEquals("Poor snake, you made it without a head!", exception.getMessage());
    }
}