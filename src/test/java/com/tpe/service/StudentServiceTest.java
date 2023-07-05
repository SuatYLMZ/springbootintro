package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // to enable Mockito's extension for JUnit5 tests
class StudentServiceTest {

    @Mock //to create a mock (fake) instance of the StudentRepository
    private StudentRepository studentRepository;

    @InjectMocks // Injects Mocked dependency into this instance
    private StudentService studentService = new StudentService();

    Student student1;

    @BeforeEach //before each of my test methods new object will be  created
        // name, lastName, email, phoneNumber will be set with data inside of setUp
    void setUp() {
        student1 = new Student();
        student1.setName("Burak");
        student1.setLastName("Burak");
        student1.setEmail("burak@gmail.com");
        student1.setPhoneNumber("999999");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("to check getAll method is returning list of students")
    void getAllStudents() {

        //given
        //create some sample students

        Student student2 = new Student();
        student2.setName("Sinan");
        student2.setLastName("Sinan");
        student2.setEmail("sinan@gmail.com");
        student2.setPhoneNumber("888");

        //creating expected result
        List<Student> expectedStudent = Arrays.asList(student1, student2);
        //since we have mocked StudentRepository, findAll() will return list of student
        when(studentRepository.findAll()).thenReturn(expectedStudent);

        //when --Actual

        List<Student> actualStudents = studentService.getAllStudents();

        //assert
        assertEquals(expectedStudent, actualStudents);
        //we have to verify how many times mock method is running
        verify(studentRepository, times(1)).findAll();

    }


    @Nested //to group related tests together for better organization and readability
    public class TestSaveMethod{

        @DisplayName("Test when there is unique email")
        @Test
        void saveStudentWhenThereIsNoRegisteredEmail() {
            //given
            //there student1.getEmail() is not found in DB
            when(studentRepository.existsByEmail(student1.getEmail())).thenReturn(false);

            //when
            studentService.saveStudent(student1);

            //assert
            //when there is no the same email, save() method should run once
            verify(studentRepository, times(1)).save(student1);
        }


        @DisplayName("Test when email is already in database")
        @Test
        void saveStudentWhenThereIsRegisteredEmail() {

            //given
            //when the same email is already registered in DB
            when (studentRepository.existsByEmail(student1.getEmail())).thenReturn(true);
            //when
            Exception exception = assertThrows(ConflictException.class, ()->{
                studentService.saveStudent(student1);
            });
            //assert

            assertEquals("Email already exists in DB", exception.getMessage());

            //if i am getting exception, our save method should not be running
            verify(studentRepository, never()).save(student1);

        }
    }



    @Disabled
    @Test
    void findStudentById() {
    }

    @Disabled
    @Test
    void deleteStudentById() {
    }

    @Disabled
    @Test
    void updateStudent() {
    }

    @Disabled
    @Test
    void getAllStudentWithPage() {
    }
}