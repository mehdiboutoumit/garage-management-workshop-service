package org.example.garagemanagementworkshopservice.repositories;

import org.example.garagemanagementworkshopservice.models.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
