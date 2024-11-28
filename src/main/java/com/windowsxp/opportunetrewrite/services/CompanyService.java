package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.entities.CompanyDetail;
import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.exceptions.custom.CompanyNotFoundException;
import com.windowsxp.opportunetrewrite.exceptions.custom.UserNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.CompanyDetailsRepository;
import com.windowsxp.opportunetrewrite.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyDetailsRepository companyDetailsRepository;

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));
    }

    public Company getCompanyByEmail(String email) {
        return companyRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " is not found"));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public boolean isCompanyExistById(Long id) {
        return companyRepository.existsById(id);
    }

    public boolean isCompanyExistByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    public CompanyDetail getCompanyDetail(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

        return company.getCompanyDetail();
    }

    public CompanyDetail updateCompanyDetail(Long id, Map<String, Object> updates, String email) {
        Company company = validateCompanyAccess(id, email);

        CompanyDetail companyDetail = company.getCompanyDetail();

        updates.forEach((key, value) -> {
            switch (key) {
                case "description" -> {
                    companyDetail.setDescription((String) value);
                }
                case "contactEmail" -> {
                    companyDetail.setContactEmail((String) value);
                }
                default -> {
                    throw new IllegalArgumentException("Unknown field: " + key);
                }
            }
        });

        return companyDetailsRepository.save(companyDetail);
    }

    public void deleteCompany(Long id, String email) {
        validateCompanyAccess(id, email);

        companyRepository.deleteById(id);
    }

    public List<Vacancy> getVacancies(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

        return company.getVacancies();
    }

    public Company validateCompanyAccess(Long id, String email) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

        if (!company.getEmail().equals(email)) {
            throw new AccessDeniedException("You do not have permission to do this.");
        }

        return company;
    }
}
