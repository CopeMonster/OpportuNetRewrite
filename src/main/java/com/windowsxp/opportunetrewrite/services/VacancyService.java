package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.dto.VacancyCreateRequest;
import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.entities.VacancyDetail;
import com.windowsxp.opportunetrewrite.entities.enums.Currency;
import com.windowsxp.opportunetrewrite.entities.enums.EmploymentType;
import com.windowsxp.opportunetrewrite.entities.enums.ExperienceType;
import com.windowsxp.opportunetrewrite.entities.enums.WorkScheduleType;
import com.windowsxp.opportunetrewrite.exceptions.custom.StudentNotRespondVacancyException;
import com.windowsxp.opportunetrewrite.exceptions.custom.VacancyNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.VacancyDetailsRepository;
import com.windowsxp.opportunetrewrite.repositories.VacancyRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final CompanyService companyService;
    private final VacancyDetailsRepository vacancyDetailsRepository;
    private final StudentService studentService;

    public Vacancy getVacancyById(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException("Vacancy with id " + id + " is not found"));
    }

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    @Transactional
    public Vacancy createVacancy(VacancyCreateRequest request, String email) {
        Company company = companyService.getCompanyByEmail(email);

        Vacancy vacancy = Vacancy.builder()
                .company(company)
                .build();

        VacancyDetail vacancyDetail = VacancyDetail.builder()
                .vacancy(vacancy)
                .title(request.getTitle())
                .description(request.getDescription())
                .requirements(request.getRequirements())
                .employmentType(EmploymentType.valueOf(request.getEmploymentType()))
                .workScheduleType(WorkScheduleType.valueOf(request.getWorkScheduleType()))
                .experienceType(ExperienceType.valueOf(request.getExperienceType()))
                .salary(request.getSalary())
                .currency(Currency.valueOf(request.getCurrency()))
                .build();

        vacancy.setVacancyDetail(vacancyDetail);

        return vacancyRepository.save(vacancy);
    }

    public void deleteVacancy(Long id, String email) {
        Vacancy vacancy = validateCompanyAccessToVacancy(id, email);

        vacancyRepository.deleteById(id);
    }

    public VacancyDetail updateVacancyDetails(Long id, Map<String, Object> updates, String email) {
        Vacancy vacancy = validateCompanyAccessToVacancy(id, email);
        VacancyDetail vacancyDetail = vacancy.getVacancyDetail();

        updates.forEach((key, value) -> {
            switch (key) {
                case "title" -> {
                    vacancyDetail.setTitle((String) value);
                }
                case "description" -> {
                    vacancyDetail.setDescription((String) value);
                }
                case "requirements" -> {
                    vacancyDetail.setRequirements((String) value);
                }
                case "employmentType" -> {
                    vacancyDetail.setEmploymentType(EmploymentType.valueOf((String) value));
                }
                case "workScheduleType" -> {
                    vacancyDetail.setWorkScheduleType(WorkScheduleType.valueOf((String) value));
                }
                case "experienceType" -> {
                    vacancyDetail.setExperienceType(ExperienceType.valueOf((String) value));
                }
                case "salary" -> {
                    vacancyDetail.setSalary((Float) value);
                }
                case "currency" -> {
                    vacancyDetail.setCurrency(Currency.valueOf((String) value));
                }
                default -> {
                    throw new IllegalArgumentException("Unknown field: " + key);
                }
            }
        });

        return vacancyDetailsRepository.save(vacancyDetail);
    }

    public List<Student> getResponders(Long id, String email) {
        Vacancy vacancy = validateCompanyAccessToVacancy(id, email);

        return vacancy.getResponders();
    }

    @Transactional
    public Pair<Vacancy, Student> applyStudentRespond(Long id, String email) {
        Vacancy vacancy = getVacancyById(id);
        Student student = studentService.getStudentByEmail(email);

        student.applyVacancy(vacancy);
        vacancy.addResponder(student);

        return Pair.of(vacancy, student);
    }

    @Transactional
    public Pair<Vacancy, Student> cancelStudentRespond(Long id, String email) {
        Vacancy vacancy = getVacancyById(id);
        Student student = studentService.getStudentByEmail(email);

        if (!vacancy.getResponders().contains(student)) {
            throw new StudentNotRespondVacancyException("Student with id " + student.getId() + " didnt respond to vacancy with id " + id);
        }

        student.cancelVacancy(vacancy);
        vacancy.removeResponder(student);

        return Pair.of(vacancy, student);
    }

    public Vacancy validateCompanyAccessToVacancy(Long id, String email) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException("Vacancy with id " + id + " is not found"));

        if (!vacancy.getCompany().getEmail().equals(email)) {
            throw new AccessDeniedException("You do not have permission to do this.");
        }

        return vacancy;
    }


}
