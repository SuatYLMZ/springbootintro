package com.tpe.day01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test06_ParameterizedTest {

    @Test
    void testLength(){
        assertTrue("Cigdem".length()>0);
        assertTrue("Sinan".length()>0);
        assertTrue("Otgo".length()>0);
    }
    //the problem above is we are repeating ourselves


    @ParameterizedTest //to indicate this test will be included some parameters
    @ValueSource(strings = {"Cigdem", "Sinan", "Otgo", "Java",""}) //to indicate value of str
    void testLength2(String str){
        assertTrue(str.length()>0);
    }


    @Test
    void testUpperCase(){
        String str1 = "Hello";
        String str2 = "Java";
        String str3 = "Test";

        assertEquals("HELLO", str1.toUpperCase());
        assertEquals("JAVA", str2.toUpperCase());
        assertEquals("TEST", str3.toUpperCase());
    }

    //coma-seperated
    @ParameterizedTest
    @CsvSource(value = {
            "HELLO, Hello",
            "JAVA, Java",
            "TEST, Test"
    })

    void testUpperCase2(String str1, String str2){
        assertEquals(str1, str2.toUpperCase());
    }

    /*
        if we want to test:
            "java".contains a-->assertTrue
            "Junit".contains u-->assertTrue
            "Test".contains a-->assertFalse


         How would u test this using CsvSource?
     */

    @ParameterizedTest
    @CsvSource({
            "true, java, a",
            "true, Junit, u",
            "false, Test, a"
    })

    void testContains(boolean b1, String str1, String str2){
        assertEquals(b1, str1.contains(str2));
    }

}
