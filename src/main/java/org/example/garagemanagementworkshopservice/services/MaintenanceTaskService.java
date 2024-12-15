package org.example.garagemanagementworkshopservice.services;

import org.example.garagemanagementworkshopservice.models.MaintenanceTask;
import org.example.garagemanagementworkshopservice.repositories.MaintenanceRepository;
import org.example.garagemanagementworkshopservice.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceTaskService {
    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceTaskService(WorkshopRepository workshopRepository, MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public MaintenanceTask createMaintenanceTask(MaintenanceTask maintenanceTask) {
        return maintenanceRepository.save(maintenanceTask);
    }
}
