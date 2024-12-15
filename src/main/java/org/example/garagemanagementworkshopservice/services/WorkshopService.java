package org.example.garagemanagementworkshopservice.services;

import org.example.garagemanagementworkshopservice.models.MaintenanceTask;
import org.example.garagemanagementworkshopservice.models.Workshop;
import org.example.garagemanagementworkshopservice.repositories.MaintenanceRepository;
import org.example.garagemanagementworkshopservice.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public WorkshopService(WorkshopRepository workshopRepository, MaintenanceRepository maintenanceRepository) {
        this.workshopRepository = workshopRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    public MaintenanceTask addTask(MaintenanceTask task) {
        return maintenanceRepository.save(task);
    }

    public List<MaintenanceTask> getTasks(long id){
        Workshop workshop = workshopRepository.findById(id).get();
        List<MaintenanceTask> tasks = new ArrayList<>();
        tasks = workshop.getMaintenanceTasks();
        return tasks;
    }



    public void updateTask(MaintenanceTask task) {
        maintenanceRepository.save(task);
    }

    public void updateDate(Workshop w, String date){
        w.setDate(date);
        workshopRepository.save(w);
    }

    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    public Workshop getWorkshopById(long id) {
        Optional<Workshop> optional = this.workshopRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Workshop not found with id: " + id);
        }
    }

    public Workshop saveWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    public void deleteWorkshopById(int id) {
        Workshop workshop = getWorkshopById(id);
        workshopRepository.delete(workshop);
    }




}
