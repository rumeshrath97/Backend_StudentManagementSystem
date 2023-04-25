package com.example.Studentms.Repository;

import com.example.Studentms.Entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    Page<Program> findAll(Pageable pageable);

    Optional<Program> findById(Long id);
}
