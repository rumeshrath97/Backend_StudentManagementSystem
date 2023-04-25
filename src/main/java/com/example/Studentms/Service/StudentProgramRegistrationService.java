package com.example.Studentms.Service;

import com.example.Studentms.Entity.Program;
import com.example.Studentms.Entity.Student;
import com.example.Studentms.Entity.StudentProgramRegistration;
import com.example.Studentms.Entity.StudentProgramRegistrationId;
import com.example.Studentms.Repository.ProgramRepository;
import com.example.Studentms.Repository.StudentProgramRegistrationRepository;
import com.example.Studentms.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StudentProgramRegistrationService {
    @Autowired
    private StudentProgramRegistrationRepository registrationRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProgramRepository programRepository;
    private Long id;

    public void registerStudentToProgram(Student student, Program program, LocalDate registerDate) {
        StudentProgramRegistration registration = new StudentProgramRegistration();
        registration.setStudent(student);
        registration.setProgram(program);
        registration.setRegisterDate(registerDate);
        registrationRepository.save(registration);
    }

    public void unregisterStudentFromProgram(Student student, Program program) {
        StudentProgramRegistrationId id = new StudentProgramRegistrationId();
        id.setStudent(student.getId());
        id.setProgram(program.getId());
        registrationRepository.deleteById(id);
    }

    public Optional<Student> getStudentById(Long studentId) {

        return studentRepository.findById(id);
    }

    public Optional<Program> getProgramById(Long programId) {
        return programRepository.findById(id);
    }
}

