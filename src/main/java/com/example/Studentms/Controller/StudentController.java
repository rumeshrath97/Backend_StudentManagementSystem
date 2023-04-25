package com.example.Studentms.Controller;

import com.example.Studentms.Entity.Student;
import com.example.Studentms.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        List<Student> students = studentService.getAllStudents(page, size);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> optionalStudent = studentService.getStudentById(id);
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(optionalStudent.get());
        }
        return ResponseEntity.noContent().build();
    }
        @PostMapping
        public ResponseEntity<Student> createStudent(@RequestBody Student student) {
            Student createdStudent = studentService.createStudent(student);
            return ResponseEntity.created(URI.create("/students/" + createdStudent.getId())).body(createdStudent);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
            Student student = studentService.updateStudent(id, updatedStudent);
            if (student != null) {
                return ResponseEntity.ok(student);
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }

        @PostMapping("/{studentId}/programs/{programId}")
        public ResponseEntity<Void> registerStudentToProgram(@PathVariable Long studentId,
                @PathVariable Long programId,
                @RequestParam(name = "registerDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registerDate) {
            studentService.registerStudentToProgram(studentId, programId, registerDate);
            return ResponseEntity.noContent().build();
        }
    }