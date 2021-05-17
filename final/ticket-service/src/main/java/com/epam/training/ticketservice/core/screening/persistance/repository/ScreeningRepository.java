package com.epam.training.ticketservice.core.screening.persistance.repository;

import com.epam.training.ticketservice.core.screening.persistance.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
}
