package com.windowsxp.opportunetrewrite.repositories;

import com.windowsxp.opportunetrewrite.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
