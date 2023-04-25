package com.example.Studentms.Repository;

import com.example.Studentms.Entity.Program;
import com.example.Studentms.Entity.Student;
import com.example.Studentms.Entity.StudentProgramRegistration;
import com.example.Studentms.Entity.StudentProgramRegistrationId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface StudentProgramRegistrationRepository extends JpaRepository<StudentProgramRegistration, StudentProgramRegistrationId> {
    @Modifying
    @Transactional
    @Query("INSERT INTO StudentProgramRegistration(student, program, registerDate) " + "VALUES (:student, :program, :registerDate)")
    void registerStudentToProgram(@Param("student") Student student,
                                  @Param("program") Program program,
                                  @Param("registerDate") LocalDate registerDate);
}

