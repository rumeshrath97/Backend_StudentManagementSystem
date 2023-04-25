package com.example.Studentms.Service;

import com.example.Studentms.Entity.Program;
import com.example.Studentms.Repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms(int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        return programRepository.findAll(pageable).getContent();
    }

    public Optional<Program> getProgramById(Long id) {
        return programRepository.findById(id);
    }

    public Program createProgram(Program program) {
        return programRepository.save(program);
    }

    public Program updateProgram(Long id, Program updatedProgram) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setName(updatedProgram.getName());
            program.setDuration(updatedProgram.getDuration());
            program.setCost(updatedProgram.getCost());
            return programRepository.save(program);
        }
        return null;
    }

    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }
}
