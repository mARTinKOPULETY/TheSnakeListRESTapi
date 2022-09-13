package com.cg.snakeList.repo;

import com.cg.snakeList.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByUserNameIs(String userName);




}
