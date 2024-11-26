package com.windowsxp.opportunetrewrite.repositories;

import com.windowsxp.opportunetrewrite.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}