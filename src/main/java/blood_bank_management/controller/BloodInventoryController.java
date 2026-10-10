package blood_bank_management.controller;

import blood_bank_management.entity.BloodInventory;
import blood_bank_management.service.BloodInventoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class BloodInventoryController {

    private final BloodInventoryService bloodInventoryService;

    public BloodInventoryController(BloodInventoryService bloodInventoryService) {
        this.bloodInventoryService = bloodInventoryService;
    }

    // Add blood inventory
    @PostMapping
    public ResponseEntity<BloodInventory> addInventory(
            @RequestBody BloodInventory inventory) {

        return ResponseEntity.ok(
                bloodInventoryService.addBloodInventory(inventory));
    }

    // Get all blood inventory
    @GetMapping
    public ResponseEntity<List<BloodInventory>> getAllInventory() {

        return ResponseEntity.ok(
                bloodInventoryService.getAllBloodInventory());
    }

    // Get inventory by ID
    @GetMapping("/{id}")
    public ResponseEntity<BloodInventory> getInventoryById(
            @PathVariable Long id) {

        return bloodInventoryService
                .getBloodInventoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search blood availability by blood group
    @GetMapping("/search/{bloodGroup}")
    public ResponseEntity<BloodInventory> getBloodAvailability(
            @PathVariable String bloodGroup) {

        return bloodInventoryService
                .getBloodAvailability(bloodGroup)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update inventory
    @PutMapping("/{id}")
    public ResponseEntity<BloodInventory> updateInventory(
            @PathVariable Long id,
            @RequestBody BloodInventory inventory) {

        return ResponseEntity.ok(
                bloodInventoryService.updateBloodInventory(id, inventory));
    }

    // Delete inventory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(
            @PathVariable Long id) {

        bloodInventoryService.deleteBloodInventory(id);

        return ResponseEntity.noContent().build();
    }
}
