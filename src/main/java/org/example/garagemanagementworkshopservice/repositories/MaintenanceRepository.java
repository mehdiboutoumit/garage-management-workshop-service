package org.example.garagemanagementworkshopservice.repositories;

import org.example.garagemanagementworkshopservice.models.MaintenanceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceTask, Long> {
}
