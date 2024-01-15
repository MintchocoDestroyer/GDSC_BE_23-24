package com.example.gdsc;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll(){
        System.out.println("BeforeAll\n");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("AfterAll\n");
    }
    @BeforeEach
    void beforeEach(){
        System.out.println("BeforeEach\n");
    }
    @AfterEach
    void afterEach(){
        System.out.println("AfterEach\n");
    }

   @Test
    void test1(){
       System.out.println("test1\n");
   }
    @Test
    @DisplayName("Test Case2!!!")
    void test2(){
        System.out.println("test2\n");
    }
    @Test
    @Disabled
    void test3(){
        System.out.println("test3\n");
    }
}
