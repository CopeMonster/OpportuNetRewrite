package com.windowsxp.opportunetrewrite.repositories;

import com.windowsxp.opportunetrewrite.entities.VacancyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyDetailsRepository extends JpaRepository<VacancyDetail, Long> {
}
