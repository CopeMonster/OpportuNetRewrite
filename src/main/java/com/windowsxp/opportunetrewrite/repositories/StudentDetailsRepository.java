package com.windowsxp.opportunetrewrite.repositories;

import com.windowsxp.opportunetrewrite.entities.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetail, Long> {
}
