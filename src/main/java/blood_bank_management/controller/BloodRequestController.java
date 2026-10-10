package blood_bank_management.controller;

import blood_bank_management.entity.BloodRequest;
import blood_bank_management.service.BloodRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-requests")
@CrossOrigin(origins = "*")
public class BloodRequestController {

    private final BloodRequestService bloodRequestService;

    public BloodRequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }

    @PostMapping
    public BloodRequest addBloodRequest(
            @RequestBody BloodRequest bloodRequest) {

        return bloodRequestService.addBloodRequest(bloodRequest);
    }

    @GetMapping
    public List<BloodRequest> getAllBloodRequests() {

        return bloodRequestService.getAllBloodRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodRequest> getBloodRequestById(
            @PathVariable Long id) {

        return bloodRequestService.getBloodRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public BloodRequest updateBloodRequest(
            @PathVariable Long id,
            @RequestBody BloodRequest requestDetails) {

        return bloodRequestService.updateBloodRequest(
                id,
                requestDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBloodRequest(
            @PathVariable Long id) {

        bloodRequestService.deleteBloodRequest(id);

        return ResponseEntity.ok(
                "Blood request deleted successfully");
    }
}