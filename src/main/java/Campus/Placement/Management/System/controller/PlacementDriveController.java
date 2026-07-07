package Campus.Placement.Management.System.controller;

import Campus.Placement.Management.System.model.PlacementDrive;
import Campus.Placement.Management.System.repository.PlacementDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drives")
@CrossOrigin(origins = "http://localhost:3000")
public class PlacementDriveController {

    @Autowired
    private PlacementDriveRepository driveRepository;

    @GetMapping("/all")
    public List<PlacementDrive> getAllDrives() {
        return driveRepository.findAll();
    }
}