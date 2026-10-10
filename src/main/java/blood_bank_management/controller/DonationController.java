package blood_bank_management.controller;

import blood_bank_management.entity.Donation;
import blood_bank_management.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // Add a new donation
    @PostMapping
    public Donation addDonation(@RequestBody Donation donation) {
        return donationService.addDonation(donation);
    }

    // Get all donations
    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    // Get donation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationById(
            @PathVariable Long id) {

        return donationService.getDonationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update donation
    @PutMapping("/{id}")
    public Donation updateDonation(
            @PathVariable Long id,
            @RequestBody Donation donationDetails) {

        return donationService.updateDonation(id, donationDetails);
    }

    // Delete donation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonation(
            @PathVariable Long id) {

        donationService.deleteDonation(id);

        return ResponseEntity.ok(
                "Donation deleted successfully");
    }
}