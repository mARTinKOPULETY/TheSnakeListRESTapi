package com.cg.snakeList.entity;

import javax.persistence.*;

@Entity
@Table(name = "snake")
public class Snake {

    /* automaticly generated id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;
    private  String pic;

    public Snake() {
    }

    public Snake(Long id, String name, String pic) {
        this.id = id;
        this.name = name;
        this.pic = pic;
    }

    /*getters & setters*/
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Snake{" + "id=" + id + ", name='" + name + '\'' + ", pic='" + pic + '\'' + '}';
    }
}
