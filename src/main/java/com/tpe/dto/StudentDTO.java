package com.tpe.dto;


import com.tpe.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;

    @NotNull(message = "first cannot be null")
    @NotBlank(message= "name cannot be white space")
    @Size(min=2, max = 25, message = "Fist name '${validatedValue}' must be between {min} and {max}" )

    private String firstName; //


    @NotNull(message = "last cannot be null")
    @NotBlank(message= "last name cannot be white space")
    @Size(min=2, max = 25, message = "Last name '${validatedValue}' must be between {min} and {max}" )
    private   String lastName;
    //@Setter
    private Integer grade;


    @Email(message="please provide valid email") //it will check "@" and "."
    private  String email;

    private String phoneNumber;


    private LocalDateTime createdDate =  LocalDateTime.now();

    public StudentDTO (Student student){    //StudentDTO studentDTO = new StudentDTO(student)
        this.id = student.getId();
        this.firstName = student.getName();
        this.lastName = student.getLastName();
        this.grade = student.getGrade();
        this.email = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
        this.createdDate = student.getCreatedDate();
    }

    public StudentDTO (String name, String lastname){
        this.firstName = name;
        this.lastName = lastname;
    }

}
