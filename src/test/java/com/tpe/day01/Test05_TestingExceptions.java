package com.tpe.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test05_TestingExceptions {

    //we need to test if we are getting expected exceptions

    @Test
    void testException(){
        String str = "JUnit is a framework to test JAVA codes";
        String str2 = null;
        assertThrows(NullPointerException.class, ()->{
            System.out.println("testException() executed");
            str2.length();
        });
    }

    @Test
    void testException2(){
        int num1 = 4;
        int num2 = 0;
        assertThrows(ArithmeticException.class, ()-> System.out.println(num1/num2));

    }

    //Instead of ArithmeticException if we use RuntimeException it also pass, because
    //RuntimeException is a parent class of ArithmeticException
    // but we should test more specific exceptions
}
