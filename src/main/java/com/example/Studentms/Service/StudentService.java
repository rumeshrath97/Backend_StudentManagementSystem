package com.example.Studentms.Service;

import com.example.Studentms.Entity.Program;
import com.example.Studentms.Entity.Student;
import com.example.Studentms.Entity.StudentProgramRegistration;
import com.example.Studentms.Repository.ProgramRepository;
import com.example.Studentms.Repository.StudentProgramRegistrationRepository;
import com.example.Studentms.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentProgramRegistrationRepository registrationRepository;
    private ProgramRepository programRepository;

    public List<Student> getAllStudents(int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        return studentRepository.findAll(pageable).getContent();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(updatedStudent.getName());
            student.setAddress(updatedStudent.getAddress());
            student.setContact(updatedStudent.getContact());
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public void registerStudentToProgram(Long studentId, Long programId, LocalDate registerDate) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Program> optionalProgram = programRepository.findById(programId);
        if (optionalStudent.isPresent() && optionalProgram.isPresent()) {
            Student student = optionalStudent.get();
            Program program = optionalProgram.get();
            StudentProgramRegistration registration = new StudentProgramRegistration();
            registration.setStudent(student);
            registration.setProgram(program);
            registration.setRegisterDate(registerDate);
            registrationRepository.save(registration);
        }
    }
}


