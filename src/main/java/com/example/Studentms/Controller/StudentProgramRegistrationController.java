package com.example.Studentms.Controller;

import com.example.Studentms.Entity.Program;
import com.example.Studentms.Entity.Student;
import com.example.Studentms.Service.StudentProgramRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/registrations")
public class StudentProgramRegistrationController {
    @Autowired
    private StudentProgramRegistrationService registrationService;

    @PostMapping("/{studentId}/programs/{programId}")
    public ResponseEntity<Void> registerStudentToProgram(@PathVariable Long studentId,
                                                         @PathVariable Long programId,
                                                         @RequestParam(name = "registerDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registerDate) {
        Optional<Student> optionalStudent = registrationService.getStudentById(studentId);
        Optional<Program> optionalProgram = registrationService.getProgramById(programId);
        if (optionalStudent.isPresent() && optionalProgram.isPresent()) {
            Student student = optionalStudent.get();
            Program program = optionalProgram.get();
            registrationService.registerStudentToProgram(student, program, registerDate);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{studentId}/programs/{programId}")
    public ResponseEntity<Void> unregisterStudentFromProgram(@PathVariable Long studentId,
                                                             @PathVariable Long programId) {
        Optional<Student> optionalStudent = registrationService.getStudentById(studentId);
        Optional<Program> optionalProgram = registrationService.getProgramById(programId);
        if (optionalStudent.isPresent() && optionalProgram.isPresent()) {
            Student student = optionalStudent.get();
            Program program = optionalProgram.get();
            registrationService.unregisterStudentFromProgram(student, program);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}



