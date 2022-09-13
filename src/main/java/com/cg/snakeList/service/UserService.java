package com.cg.snakeList.service;

import com.cg.snakeList.entity.*;
import com.cg.snakeList.exceptions.*;
import com.cg.snakeList.repo.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
public class UserService {
    private final UserRepo userRepo;
    static Long idFromUser;
    public UserService(UserRepo userRepo){this.userRepo=userRepo;}

    public List<User> getAllUsers(){return  userRepo.findAll();}

    public void addUser(@Param("userConfirmPassword")String userConfirmPassword, User user ){
        //show list if the user is in db, if  not its size is 0
        List<User> isPresent=userRepo.findByUserNameIs(user.getUserName());
        int size= isPresent.size();

        if (user.getUserName().isBlank()){
                throw  new IllegalInputException("Insert a NAME of a user please!");
        }
        else if (size!=0) {
            throw new IllegalInputException("The user "+ user.getUserName()+ " is already existssSSss:<");
        }
        else  if (user.getUserPassword().isBlank()){
            throw new IllegalInputException("Insert a PASSWORD of a user please!");
        }
        else if(!user.getUserPassword().equals(userConfirmPassword)){
            throw new IllegalInputException("Validation of the password is incorect.");
        }
        else {
            userRepo.save(user);// snake value is what we want to capture in Test
        }
    }
    @Transactional
    public void updateUser( @Param("userConfirmPassword")String userConfirmPassword,
                            Long userId,
                           String userName,
                           String userPassword
                          ) {
      User user = userRepo.findById(userId).orElseThrow(() -> new IllegalInputException("There is not a user with user's id " + userId + "."));
        //show list if the user is in db, if not its size is 0
      List<User> isPresent=userRepo.findByUserNameIs(user.getUserName());
        int size= isPresent.size();
        /*checks if there is  a users name entered that should be updated*/
        if ( !userName.isBlank() &&  !userName.equals(userRepo.findByUserNameIs(userName))) {
            user.setUserName(userName);
        } else if (userName.equals(user.getUserName())) {
            throw new IllegalInputException("The " + userName + " is already in the database, try make another snakeName!");
        } else {
            throw new IllegalInputException("Insert a NAME of a user please!");
        }
        //checks if pass is  inserted correctly - not blank, not the same pass likes before
        if (!userPassword.isBlank() && !userPassword.equals(user.getUserPassword())) {
            if(userPassword.equals(userConfirmPassword)) {
                user.setUserPassword(userPassword);
            }else {
                throw new IllegalInputException("Validation of the password is incorrect.");
            }
        }else if (userPassword.equals(user.getUserPassword())) {
            throw  new IllegalInputException("You can't use the same password: " + userPassword);
        }else {

            throw new IllegalInputException("Insert a PASSWORD please!");
        }
    }


    public void deleteUser(Long userId) {
        userRepo.findById(userId).orElseThrow(() -> new IllegalInputException("There is not a user with id " + userId + "."));
        userRepo.deleteById(userId);

    }
    public void deleteAllUsers(){ userRepo.deleteAll();}

    public void loginUser(Long userId, String userName, String userPassword, User user) {
        //show list if the user is in db, if  not its size is 0
        List<User> isPresent = userRepo.findByUserNameIs(user.getUserName());
        int size = isPresent.size();
        if (size != 0) {
            //lists list of user to get its own atributes
            for (User userInDB : isPresent) {
                if (userInDB.getUserName().equals(userName)) {
                    if (userInDB.getUserPassword().equals(userPassword)) {
                        idFromUser = userInDB.getUserId(); //sets idFromUser for creating new snake. in snake service userId = idFromUser
                    } else throw new IllegalInputException("Incorect password");
                }
            }
        } else throw new IllegalInputException("There is no \""+user.getUserName()+"\"user!");
    }

    public void logoutUser(){ idFromUser =null;} //It's not possible to make new snake if there is null





}




