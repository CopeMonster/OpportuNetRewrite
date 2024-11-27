package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.dto.VacancyCreateRequest;
import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.entities.VacancyDetail;
import com.windowsxp.opportunetrewrite.entities.enums.Currency;
import com.windowsxp.opportunetrewrite.entities.enums.EmploymentType;
import com.windowsxp.opportunetrewrite.entities.enums.ExperienceType;
import com.windowsxp.opportunetrewrite.entities.enums.WorkScheduleType;
import com.windowsxp.opportunetrewrite.exceptions.custom.VacancyNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Vacancy createVacancy(VacancyCreateRequest request, Company company) {
        Vacancy vacancy = new Vacancy();
        vacancy.setCompany(company);

        VacancyDetail vacancyDetail = new VacancyDetail();
        vacancyDetail.setVacancy(vacancy);
        vacancyDetail.setTitle(request.getTitle());
        vacancyDetail.setDescription(request.getDescription());
        vacancyDetail.setRequirements(request.getRequirements());
        vacancyDetail.setEmploymentType(EmploymentType.valueOf(request.getEmploymentType()));
        vacancyDetail.setWorkScheduleType(WorkScheduleType.valueOf(request.getWorkScheduleType()));
        vacancyDetail.setExperienceType(ExperienceType.valueOf(request.getExperienceType()));
        vacancyDetail.setSalary(request.getSalary());
        vacancyDetail.setCurrency(Currency.valueOf(request.getCurrency()));

        vacancy.setVacancyDetail(vacancyDetail);

        return vacancyRepository.save(vacancy);
    }

    public void deleteVacancy(Long id, Company company) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException("Vacancy with id " + id + " is not found"));

        if (!vacancy.getCompany().getEmail().equals(company.getEmail())) {
            throw new AccessDeniedException("You do not have permission to do this.");
        }

        vacancyRepository.deleteById(id);
    }

    public List<Vacancy> getCompanyVacancies(Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        return company.getVacancies();
    }


}
