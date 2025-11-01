package com.yuuto.activos.Domain.Entities.Suscriptions;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Table(name = "company")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", length = 150, nullable = false)
    private String companyName;

    @Column(name = "company_nit", length = 50, nullable = false)
    private String companyNit;

    @Column(name = "company_address", length = 255, nullable = true)
    private String companyAddress;

    @Column(name = "companycity", length = 255, nullable = true)
    private String companycity;

    @Column(name = "companycountry", length = 255, nullable = true)
    private String companycountry;

    @Column(name = "companycontactEmail", length = 255, nullable = true)
    private String companycontactEmail;

    @Column(name = "companycontactPhone", length = 255, nullable = true)
    private String companycontactPhone;

   @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void onCreate() {
    this.createdAt = LocalDateTime.now();

    }
}
