package com.tpe.day01;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Test04_BeforeAll_AfterAll {

    //@BeforeAll method must be static unless the test class is
    // annotated with @TestInstance(Lifecycle.PER_CLASS)
    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll() is executed...");
    }
    @AfterAll
    static  void afterAll(){
        System.out.println("afterAll() is executed...");
    }

    @Test
    void  testArrays(TestInfo info) { //TestInfo class gives information about running class
        String str = "Unit test with JUNIT";
        String [] actual = str.split(" ");
        String [] extected ={"Unit", "test", "with", "JUNIT"};
        System.out.println(info.getDisplayName()+" executed");
        assertArrayEquals(extected, actual, "arrays are not equal");
    }

    @Test
    void  testArrays2(TestInfo info) { //TestInfo class gives information about running class
        String str = "Tests should be isolated";
        String [] actual = str.split(" ");
        String [] extected ={"Tests", "should", "be", "isolated"};
        System.out.println(info.getDisplayName()+" executed");
        assertArrayEquals(extected, actual, "arrays are not equal");
    }
}
