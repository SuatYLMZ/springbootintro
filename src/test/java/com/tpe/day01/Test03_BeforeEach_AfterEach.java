package com.tpe.day01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test03_BeforeEach_AfterEach {

    //@BeforeEach annotation executes annotated classes before
    //each method
    //@AfterEach annotation executes annotated classes after
    //each method

    String str;

    @BeforeEach
    void beforeEach(){
        //assign value for str before each test method
        str = "Test methods should be simple";
        System.out.println("******* beforeEach() executed *******");
    }

    @AfterEach()
    void afterEach(){
        str = "";
        System.out.println("******* afterEach() executed *******");
    }

    @Test
    void testArray(TestInfo info){
        String [] actual = str.split(" ");
        String [] expected = {"Test", "methods", "should", "be", "simple"};
        assertTrue(Arrays.equals(expected, actual));
        System.out.println(info.getDisplayName()+ " executed");
    }

    @Test
    void testStringLength(TestInfo info){
        int actual = str.length();
        int expected = 29;
        assertEquals(expected, actual);
        System.out.println(info.getDisplayName()+ " executed");
    }
}
