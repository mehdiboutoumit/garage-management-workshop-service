package org.example.garagemanagementworkshopservice.controllers;


import org.example.garagemanagementworkshopservice.models.MaintenanceTask;
import org.example.garagemanagementworkshopservice.models.Workshop;
import org.example.garagemanagementworkshopservice.services.MaintenanceTaskService;
import org.example.garagemanagementworkshopservice.services.WorkshopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshop")
public class workshopController {

    private final WorkshopService workshopService;
    private final MaintenanceTaskService maintenanceTaskService;

    public workshopController(WorkshopService workshopService, MaintenanceTaskService maintenanceTaskService) {
        this.workshopService = workshopService;
        this.maintenanceTaskService = maintenanceTaskService;
    }

    @PostMapping("/")
    public Workshop createWorkshop(@RequestBody Workshop workshop) {
        return workshopService.saveWorkshop(workshop);
    }

    @GetMapping("/")
    public List<Workshop> getAllWorkshops() {
        return workshopService.getAllWorkshops();
    }

    @GetMapping("/{id}")
    public Workshop getWorkshopById(@PathVariable int id) {
        return workshopService.getWorkshopById(id);
    }

    @GetMapping("/{workshopId}/tasks")
    public List<MaintenanceTask> getMaintenanceTasks(@PathVariable int workshopId) {
        return workshopService.getTasks(workshopId);
    }

    @PostMapping("/{workshopId}/tasks")
    public Workshop addMaintenanceTask(@RequestBody MaintenanceTask maintenanceTask, @PathVariable int workshopId) {
        Workshop workshop = workshopService.getWorkshopById(workshopId);
        workshop.getMaintenanceTasks().add(maintenanceTask);
        maintenanceTaskService.createMaintenanceTask(maintenanceTask);
        return workshopService.saveWorkshop(workshop);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkshopById(@PathVariable int id) {
         workshopService.deleteWorkshopById(id);
    }


}
