package com.windowsxp.opportunetrewrite.controllers;

import com.windowsxp.opportunetrewrite.assemblers.VacancyAssembler;
import com.windowsxp.opportunetrewrite.dto.responses.StudentDTO;
import com.windowsxp.opportunetrewrite.dto.requests.VacancyCreateRequest;
import com.windowsxp.opportunetrewrite.dto.responses.VacancyDTO;
import com.windowsxp.opportunetrewrite.dto.responses.VacancyRespondResponseDTO;
import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.entities.VacancyDetail;
import com.windowsxp.opportunetrewrite.services.CompanyService;
import com.windowsxp.opportunetrewrite.services.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final CompanyService companyService;
    private final VacancyAssembler vacancyAssembler;

    @GetMapping("/{vacancyId}")
    public EntityModel<VacancyDTO> getVacancyById(@PathVariable Long vacancyId) {
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        return vacancyAssembler.toModel(VacancyDTO.from(vacancy));
    }

    @GetMapping
    public CollectionModel<EntityModel<VacancyDTO>> getAllVacancies() {
        List<VacancyDTO> vacancyDTOS = vacancyService.getVacancies()
                .stream()
                .map(VacancyDTO::from)
                .toList();

        List<EntityModel<VacancyDTO>> vacancies = vacancyDTOS
                .stream()
                .map(vacancyAssembler::toModel)
                .toList();

        return CollectionModel.of(vacancies);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<VacancyDTO> createVacancy(
            @Valid @RequestBody VacancyCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Vacancy vacancy = vacancyService.createVacancy(request, userDetails.getUsername());
        VacancyDTO vacancyDTO = VacancyDTO.from(vacancy);

        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyDTO);
    }

    @PatchMapping("/{vacancyId}")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<VacancyDTO> updateVacancy(
            @PathVariable Long vacancyId,
            @Valid @RequestBody Map<String, Object> updates,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        VacancyDetail vacancyDetail =
                vacancyService.updateVacancyDetails(vacancyId, updates, userDetails.getUsername());

        return ResponseEntity.ok(VacancyDTO.from(vacancyDetail.getVacancy()));
    }

    @DeleteMapping("/{vacancyId}")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<String> deleteVacancy(
            @PathVariable Long vacancyId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        vacancyService.deleteVacancy(vacancyId, userDetails.getUsername());

        return ResponseEntity.ok("Company with id " + vacancyId + " was deleted");
    }

    @GetMapping("/{vacancyId}/responders")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<List<StudentDTO>> getResponders(
            @PathVariable Long vacancyId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<Student> responders = vacancyService.getResponders(vacancyId, userDetails.getUsername());
        List<StudentDTO> studentDTOS = responders
                .stream()
                .map(StudentDTO::from)
                .toList();

        return ResponseEntity.ok(studentDTOS);
    }

    @PostMapping("/{vacancyId}respond")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<VacancyRespondResponseDTO> applyRespond(
            @PathVariable Long vacancyId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Pair<Vacancy, Student> vacancyStudent =
                vacancyService.applyStudentRespond(vacancyId, userDetails.getUsername());

        return ResponseEntity.ok(
                VacancyRespondResponseDTO.builder()
                        .studentEmail(vacancyStudent.getSecond().getEmail())
                        .vacancyTitle(vacancyStudent.getFirst().getVacancyDetail().getTitle())
                        .vacancyCompany(vacancyStudent.getFirst().getCompany().getCompanyName())
                        .vacancyDescription(vacancyStudent.getFirst().getVacancyDetail().getDescription())
                        .message("Student applied to vacancy")
                        .build()
        );
    }

    @PostMapping("/{vacancyId}cancelRespond")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<VacancyRespondResponseDTO> cancelRespond(
            @PathVariable Long vacancyId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Pair<Vacancy, Student> vacancyStudent =
                vacancyService.cancelStudentRespond(vacancyId, userDetails.getUsername());

        return ResponseEntity.ok(
                VacancyRespondResponseDTO.builder()
                        .studentEmail(vacancyStudent.getSecond().getEmail())
                        .vacancyTitle(vacancyStudent.getFirst().getVacancyDetail().getTitle())
                        .vacancyCompany(vacancyStudent.getFirst().getCompany().getCompanyName())
                        .vacancyDescription(vacancyStudent.getFirst().getVacancyDetail().getDescription())
                        .message("Student cancel vacancy reply")
                        .build()
        );
    }
}
