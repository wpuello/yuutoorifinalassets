package com.yuuto.activos.Domain.Repositories.Suscriptions;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuuto.activos.Domain.Entities.Suscriptions.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyNitOrCompanycontactEmail(String nit, String email);
}
