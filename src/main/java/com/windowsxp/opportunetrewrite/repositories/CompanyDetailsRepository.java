package com.windowsxp.opportunetrewrite.repositories;

import com.windowsxp.opportunetrewrite.entities.CompanyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetail, Long> {
}
