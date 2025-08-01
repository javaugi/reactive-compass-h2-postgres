/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisllc.instaiml.data;

import com.sisllc.instaiml.model.Pharmacy;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

@Slf4j
public class PharmacyGenerator extends DataGeneratorBase {
     
    public static Pharmacy generate() {
        Pharmacy pharmacy = Pharmacy.builder()
            .id(UUID.randomUUID().toString())
            .pharmacyCode("PHAR-" + JAVA_FAKER.number().digits(6))
            .name("PHARMA " + JAVA_FAKER.address().streetName())
            .email(JAVA_FAKER.internet().emailAddress())
            .phone(JAVA_FAKER.phoneNumber().phoneNumber())
            .address(JAVA_FAKER.address().fullAddress())
            .createdDate(JAVA_FAKER.date().past(JAVA_FAKER.number().numberBetween(30, 90), TimeUnit.DAYS).toInstant().atOffset(ZoneOffset.UTC))
            .updatedDate(JAVA_FAKER.date().past(JAVA_FAKER.number().numberBetween(1, 30), TimeUnit.DAYS).toInstant().atOffset(ZoneOffset.UTC))
            .build();
        
        return pharmacy;
    }     
    
    public static Pharmacy generate(DatabaseClient dbClient) {
        Pharmacy pharmacy = Pharmacy.builder()
            .id(UUID.randomUUID().toString())
            .pharmacyCode("PHAR-" + JAVA_FAKER.number().digits(6))
            .name("PHARMA " + JAVA_FAKER.address().streetName())
            .email(JAVA_FAKER.internet().emailAddress())
            .phone(JAVA_FAKER.phoneNumber().phoneNumber())
            .address(JAVA_FAKER.address().fullAddress())
            .createdDate(JAVA_FAKER.date().past(JAVA_FAKER.number().numberBetween(30, 90), TimeUnit.DAYS).toInstant().atOffset(ZoneOffset.UTC))
            .updatedDate(JAVA_FAKER.date().past(JAVA_FAKER.number().numberBetween(1, 30), TimeUnit.DAYS).toInstant().atOffset(ZoneOffset.UTC))
            .build();
        
        log.trace("pharmacy {}", insert(dbClient, pharmacy).subscribe());     
        return pharmacy;
    }     
    
    public static Mono<Long> insert(DatabaseClient dbClient, Pharmacy pharmacy) {
        return dbClient.sql("""
            INSERT INTO pharmacies (id, pharmacy_code, name, email, phone, address, created_date, updated_date) 
                    VALUES (:id, :pharmacyCode, :name, :email, :phone, :address, :createdDate, :updatedDate) 
            """)
            .bind("id", pharmacy.getId())
            .bind("pharmacyCode", pharmacy.getName())
            .bind("name", pharmacy.getName())
            .bind("email", pharmacy.getEmail())
            .bind("phone", pharmacy.getPhone())
            .bind("address", pharmacy.getAddress())
            .bind("createdDate", pharmacy.getCreatedDate())
            .bind("updatedDate", pharmacy.getUpdatedDate())
            .fetch()
            .rowsUpdated();
    }    
}
