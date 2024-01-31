package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanTest {

    @Autowired
    Food food;

    @Qualifier("pizza")
    @Autowired
    Food pizza;

    @Autowired
    Food chicken;

    @Test
    public void test(){
        food.eat();
        pizza.eat();
        chicken.eat();
    }
}
