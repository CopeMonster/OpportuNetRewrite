package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.exceptions.custom.VacancyNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final CompanyService companyService;

    public Vacancy getVacancyById(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException("Vacancy with id " + id + " is not found"));
    }

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public List<Vacancy> getCompanyVacancies(Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        return company.getVacancies();
    }
}
