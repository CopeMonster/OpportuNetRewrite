package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.entities.CompanyDetail;
import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.entities.StudentDetail;
import com.windowsxp.opportunetrewrite.exceptions.custom.UserNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

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

    public boolean isCompanyExistByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    public CompanyDetail updateCompanyDetail(Long id, CompanyDetail updatedDetail) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));
        CompanyDetail companyDetail = company.getCompanyDetail();

        if (companyDetail == null) {
            throw new IllegalStateException("Company with id " + id + " does not have associated details.");
        }

        companyDetail.setDescription(updatedDetail.getDescription());
        companyDetail.setContactEmail(updatedDetail.getContactEmail());

        companyRepository.save(company);

        return companyDetail;
    }
}
