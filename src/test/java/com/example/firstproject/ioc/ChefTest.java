package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // ioc 랑 di 사용하려고
class ChefTest {

    @Autowired // di
    IngredientFactory ingredientFactory; // 필드에다가 선언해야함!
    @Autowired // di
    Chef chef; // 필드에다가 선언해야함! (Component 로 등록하는거 잊으면안됨)

    @Test
    void 돈가스_요리하기() {
        // 준비
        // 요리창고
//        IngredientFactory ingredientFactory = new IngredientFactory();


//        Chef chef = new Chef(ingredientFactory);
        String menu = "돈가스";
        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "한돈 등심으로 만든 돈가스";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기() {
        // 준비
        // 요리창고
//        IngredientFactory ingredientFactory = new IngredientFactory();


//        Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";
        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "한우 꽃등심으로 만든 스테이크";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 치킨_요리하기() {
        // 준비
        // 요리창고
//        IngredientFactory ingredientFactory = new IngredientFactory();


//        Chef chef = new Chef(ingredientFactory);
        String menu = "치킨";
        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "국내산 10호 닭으로 만든 치킨";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

}