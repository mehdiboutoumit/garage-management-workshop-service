package org.example.garagemanagementworkshopservice.controllers;


import org.example.garagemanagementworkshopservice.models.MaintenanceTask;
import org.example.garagemanagementworkshopservice.models.Workshop;
import org.example.garagemanagementworkshopservice.services.MaintenanceTaskService;
import org.example.garagemanagementworkshopservice.services.NotificationPublisherService;
import org.example.garagemanagementworkshopservice.services.WorkshopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshop")
public class workshopController {

    private final WorkshopService workshopService;
    private final MaintenanceTaskService maintenanceTaskService;
    private final NotificationPublisherService notificationPublisherService;

    public workshopController(WorkshopService workshopService, MaintenanceTaskService maintenanceTaskService, NotificationPublisherService notificationPublisherService) {
        this.workshopService = workshopService;
        this.maintenanceTaskService = maintenanceTaskService;
        this.notificationPublisherService = notificationPublisherService;
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
        notificationPublisherService.sendNotification("A task has been scheduled for you vehicle");
        return workshopService.saveWorkshop(workshop);
    }

    @PutMapping("/{workshopId}/tasks")
    public Workshop updateMaintenanceTask(
            @RequestBody MaintenanceTask updatedTask,
            @PathVariable int workshopId) {

        // Fetch the workshop by ID
        Workshop workshop = workshopService.getWorkshopById(workshopId);

        // Update the task within the workshop's task list
        for (MaintenanceTask task : workshop.getMaintenanceTasks()) {
            if (task.getId() == updatedTask.getId()) { // Match by ID
                task.setStatus("Completed");
                break;
            }
        }


        return workshopService.saveWorkshop(workshop);
    }


    @DeleteMapping("/{id}")
    public void deleteWorkshopById(@PathVariable int id) {
         workshopService.deleteWorkshopById(id);
    }


}
