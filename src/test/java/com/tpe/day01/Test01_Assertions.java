package com.tpe.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01_Assertions {
    @Test // makes method runnable
    public void test(){

    }

    //starting from Junit5, "public" access modifier is optional
    //it is suggested that test name should start with Test

    //test lentgh method
    @Test
    public void testLength(){
        String str = "Hello World";
        int actualValue = str.length();
        int expectedValue = 11;
        assertEquals(expectedValue, actualValue);
    }

    //test uppercase method from core java
    @Test
    public void testUpperCase(){
        String actualValue = "hello".toUpperCase();
        String expectedValue = "HELLO";
        assertEquals(expectedValue, actualValue, "upperCase method failed");
    }

    //test Math.addExact from core java
    @Test
    public void testAdd() {
        int a = 5, b=4;

        int actual = Math.addExact(a, b);
        int expected = 9;

        assertEquals(actual, expected);
    }

    //test string.Contains
    @Test
    void testContains(){
        assertEquals(false, "JUnit".contains("z"));
    }

}
