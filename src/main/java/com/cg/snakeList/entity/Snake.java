package com.cg.snakeList.entity;

import javax.persistence.*;

@Entity
@Table(name = "snake")
public class Snake {

    /* automaticly generated id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(unique = true, name="snake_id")
    private  Long snakeId;
    @Column(name = "snake_name",nullable = false )
    private  String snakeName;
    @Column( name = "snake_pic",nullable = false)
    private  String snakePic;
    @Column( name = "user_id",nullable =false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;




    public Snake() {
    }

    public Snake(Long snakeId, String snakeName, String snakePic, Long userId) {
        this.snakeId = snakeId;
        this.snakeName = snakeName;
        this.snakePic = snakePic;
        this.userId = userId;

    }

    public Snake(String snakeName, String snakePic, Long userId) {
        this.snakeName = snakeName;
        this.snakePic = snakePic;
        this.userId = userId;
    }

    /*getters & setters*/
    public Long getSnakeId() {
        return snakeId;
    }

    public String getSnakeName() {
        return snakeName;
    }

    public void setSnakeName(String snakeName) {
        this.snakeName = snakeName;
    }

    public String getSnakePic() {
        return snakePic;
    }

    public void setSnakePic(String snakePic) {
        this.snakePic = snakePic;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Snake{" + "id=" + snakeId + ", name='" + snakeName + '\'' + ", pic='" + snakePic + '\'' + ", author=" + user.getUserId() +'}';
    }
}
