package blood_bank_management.controller;

import blood_bank_management.entity.Donor;
import blood_bank_management.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "*")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    // Add a new donor
    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorService.addDonor(donor);
    }

    // Get all donors
    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    // Get donor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id) {

        return donorService.getDonorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update donor
    @PutMapping("/{id}")
    public Donor updateDonor(
            @PathVariable Long id,
            @RequestBody Donor donorDetails) {

        return donorService.updateDonor(id, donorDetails);
    }

    // Delete donor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable Long id) {

        donorService.deleteDonor(id);

        return ResponseEntity.ok("Donor deleted successfully");
    }
}