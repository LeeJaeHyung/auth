package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Bean DI 사용가능
public class BeanTest {

    @Autowired
    Food chicken;

    @Autowired
    @Qualifier("pizza")
    Food pizza;

    @Autowired
    Food food;

    @Test
    @DisplayName("테스트")
    void test(){
        chicken.eat();
        pizza.eat();
        food.eat();
    }

}
