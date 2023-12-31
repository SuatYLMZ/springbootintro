package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController //used to create restful API
@RequestMapping("/students") //http://localhost:8080/students
public class StudentController {

    //Create logger Logger obj
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;


    //method to bring all students
    @GetMapping //http://localhost:8080/students + GET
    // @PreAuthorize("hasRole('ADMIN')") //hasRole will complete "ROLE_"
    //
    public ResponseEntity<List<Student>> getAll(){  //HTTP status code + Students list
        List <Student> students  = studentService.getAllStudents();
        return ResponseEntity.ok(students); //OK is for 200 status code
    }


    //method to create/add
    //@RequestBody --> we will send JSON data and that data should be mapped to Student object
    //@Valid --> validates the fields
    @PostMapping()  //http://localhost:8080/students + POST + JSON
    public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student){
        studentService.saveStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    //method to get Student by Id

    //Both ways will do the same thing(get values from path)
    //if there are multiple parameters, then RequestParam is suggested because we can
    // write variable names, so it will be understandable

    // using pathVariable

    @GetMapping("/{id}")  //http://localhost:8080/students/id
    public ResponseEntity<Student> getStudentByUsingPath(@PathVariable("id") Long id){
        Student student = studentService.findStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK); //return ResponseEntity.ok(students)
    }

    // using RequestParam
    @GetMapping("/query")  //http://localhost:8080/students/query?id=1
    public ResponseEntity<Student> getStudentByUsingRequestParam(@RequestParam("id") Long id){
        //if there is only one parameter we can write @RequestParam() Long id
        Student student = studentService.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    //deleteStudent From DB
    @DeleteMapping("/{id}") //http://localhost:8080/students/id + DELETE
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable() Long id) {
        studentService.deleteStudentById(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is deleted successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //update student

    @PutMapping("/{id}") ////http://localhost:8080/students/id + PUT + JSON
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody StudentDTO studentDTO){
        studentService.updateStudent(id, studentDTO);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is updaed successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // if in our DB there are 1000s of students and if we try to fetch them at the same time
    // it will take too much time may create issues

    //get all students by page

    @GetMapping("/page")  //http://localhost:8080/students/page?page=2&size=3&sort=lastName&direction=ASC + GET
    public ResponseEntity<Page<Student>> getAllStudentWithPage(
            @RequestParam("page") int page, //page number starting from 0
            @RequestParam("size") int size, //how many students we want per page
            @RequestParam("sort") String prop, //sorting field (optional)
            @RequestParam("direction")Sort.Direction direction // sorting type(optional)
    ){

        //create pageable obj to be sent to DB
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<Student>  studenstsByPage = studentService.getAllStudentWithPage(pageable);

        return ResponseEntity.ok(studenstsByPage);

    }

    //method to get students by lastName
    @GetMapping("/queryLastName") //http://localhost:8080/students/queryLastName?lastName=Bey
    public ResponseEntity<List<Student>> getStudentsByLastName(@RequestParam("lastName") String lastName){
        List<Student> students =  studentService.findStudentByLastName(lastName);
        return ResponseEntity.ok(students);
    }

    //method to get students by grade (JPQL) Java Persistence Query Language
    @GetMapping("/grade/{grade}")  //http://localhost:8080/students/grade/90
    public ResponseEntity<List<Student>> getStudentsByGrade(@PathVariable("grade") Integer grade){

        List<Student> students =  studentService.findStudentByGrade(grade);
        return ResponseEntity.ok(students);
    }



    //Can we get data as DTO from DB?
    //Using JPQL we can map entity class to DTO using the constructor we have set in DTO
    @GetMapping("/query/dto") //http://localhost:8080/students/query/dto?id=2
    public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id") Long id){

        StudentDTO studentDTO= studentService.findStudentDTOById(id);
        return ResponseEntity.ok(studentDTO);

    }

    @GetMapping("/welcome")  //http://localhost:8080/students/welcome

    public String welcome(HttpServletRequest request){

        logger.warn("----------------Welcome{}", request.getServletPath());
        return "Welcome to Student Controller";
    }

}
