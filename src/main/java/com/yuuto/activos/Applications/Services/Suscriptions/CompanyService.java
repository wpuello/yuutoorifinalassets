package com.yuuto.activos.Applications.Services.Suscriptions;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuuto.activos.Applications.Dto.Suscriptions.SuscriptionRequestDTO;
import com.yuuto.activos.Domain.Entities.Suscriptions.Company;
import com.yuuto.activos.Domain.Repositories.Suscriptions.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Company createOrGetCompany(SuscriptionRequestDTO dto) {

        // Buscar compañía existente por NIT o email
        Optional<Company> existing = companyRepository.findByCompanyNitOrCompanycontactEmail(dto.getCompanyNit(), dto.getCompanyEmail());

        if (existing.isPresent()) {
            return existing.get();
        }

        Company company = new Company();
        company.setCompanyName(dto.getCompanyName());
        company.setCompanycontactEmail(dto.getCompanyEmail());
        company.setCompanycontactPhone(dto.getCompanyPhone());
        company.setCompanyAddress(dto.getCompanyAddress());
        company.setCompanyNit(dto.getCompanyNit());
        company.setCreatedAt(LocalDateTime.now());

        return companyRepository.save(company);
    }
    
}
