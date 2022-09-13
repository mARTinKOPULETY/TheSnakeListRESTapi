package com.cg.snakeList.controller;

import com.cg.snakeList.entity.Snake;
import com.cg.snakeList.service.SnakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/snake")
public class SnakeController {

    /*instance of snakeService*/
    @Autowired
    private SnakeService snakeService;

    /* dependency injection*/
    /*@Autowired
    public SnakeController(SnakeService snakeService){this.snakeService=snakeService;}
*/
    /*returns list with all snakes*/
    @GetMapping
    public List<Snake> getAllSnakes(){
        return snakeService.getAllSnakes();
    }

    /*returns list with all snakes that contains concrete letter(s) by name*/
    @GetMapping(path = "nametest")
    public List<Snake> getSnakeByNameTEST(@RequestParam String letter){
        return  snakeService.getNameByLetterTEST(letter);
    }

    @GetMapping(path = "name")
    public List<Snake> getSnakeByNamePOSTMAN(@RequestParam String letter){
        return  snakeService.getNameByLetterPOSTMAN(letter);
    }

    /*returns list with all snakes that contains concrete letter(s) by picture*/
    @GetMapping(path = "pic")
    public  List<Snake> getSnakeByPic(@RequestParam String letter){
        return snakeService.getNameByPic(letter);
    }

    /*adds new snake*/
    @PostMapping
    public void addSnake(@RequestBody Snake snake){
        snakeService.addSnake(snake);
    }

    /*updates snake by id*/
    @PutMapping("{id}")
    public void updateSnake(
                            @RequestParam(required = false)String name,
                            @RequestParam(required = false)String pic,
                            @PathVariable Long id ){
        snakeService.updateSnake(id, name, pic);
    }

    /*deletes snake by id*/
    @DeleteMapping("{id}")
    public  void delete(@PathVariable  Long id, Snake snake ){
        snakeService.deleteSnake(id, snake);
    }


    @DeleteMapping("/deleteAllSnakes")
    public void deleteAllSnakes() {snakeService.deleteAllSnakes();}
}