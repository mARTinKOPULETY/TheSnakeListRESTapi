package com.cg.snakeList.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "user")
public class User {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @JoinColumn(unique=true, name= "user_id")
    public Long userId;

   @Column(nullable = false, name = "user_name")
    public String userName;

    @Column(nullable = false, name = "user_password")
    public String userPassword;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Snake> snake;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName='" + userName + '\'' + '}';
    }
}
