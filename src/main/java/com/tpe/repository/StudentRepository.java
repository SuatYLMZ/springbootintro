package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //optional, since we are extending from JpaRepository,
// spring will understand it is a repos class
public interface StudentRepository extends JpaRepository<Student, Long> {


    boolean existsByEmail(String email); //if there is email in DB it returns true, else false

    List<Student> findByLastName(String lastName);

    //=========JPQL=========
    @Query("SELECT  s FROM Student s WHERE s.grade=:pGrade")
    List<Student> findStudentByGrade(@Param("pGrade") Integer grade);

    //=========SQL=========

    @Query(value = "SELECT * FROM tbl_student s WHERE s.grade=:pGrade", nativeQuery = true)
    List<Student> findStudentByGradeWithSQL(@Param("pGrade") Integer grade);


    //using JPQL,  mapping student entity obj to dto
    //so we are returning studentDTO obj to service
    @Query("SELECT new com.tpe.dto.StudentDTO(std) FROM Student std WHERE std.id=:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);


    //to map some fields from entity class (name,lastName) to dto
    @Query("SELECT new com.tpe.dto.StudentDTO(std.name, std.lastName) FROM Student std WHERE std.id=:id")
    Optional<StudentDTO> findStudentDTOByIdNameAndLastName(@Param("id") Long id);


}
