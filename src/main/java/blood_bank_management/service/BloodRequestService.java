package blood_bank_management.service;

import blood_bank_management.entity.BloodInventory;
import blood_bank_management.entity.BloodRequest;
import blood_bank_management.repository.BloodInventoryRepository;
import blood_bank_management.repository.BloodRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;
    private final BloodInventoryRepository bloodInventoryRepository;

    public BloodRequestService(
            BloodRequestRepository bloodRequestRepository,
            BloodInventoryRepository bloodInventoryRepository) {

        this.bloodRequestRepository = bloodRequestRepository;
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    public BloodRequest addBloodRequest(BloodRequest bloodRequest) {
        return bloodRequestRepository.save(bloodRequest);
    }

    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestRepository.findAll();
    }

    public Optional<BloodRequest> getBloodRequestById(Long id) {
        return bloodRequestRepository.findById(id);
    }

    public BloodRequest updateBloodRequest(
            Long id,
            BloodRequest requestDetails) {

        BloodRequest request = bloodRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blood request not found"));

        String oldStatus = request.getStatus();

        request.setPatientId(requestDetails.getPatientId());
        request.setBloodGroup(requestDetails.getBloodGroup());
        request.setQuantity(requestDetails.getQuantity());
        request.setRequestDate(requestDetails.getRequestDate());
        request.setStatus(requestDetails.getStatus());

        if (!"APPROVED".equalsIgnoreCase(oldStatus)
                && "APPROVED".equalsIgnoreCase(
                        requestDetails.getStatus())) {

            BloodInventory inventory = bloodInventoryRepository.findAll()
                    .stream()
                    .filter(i -> i.getBloodGroup()
                            .equalsIgnoreCase(
                                    request.getBloodGroup()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Blood group not available"));

            if (inventory.getQuantity() < request.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient blood inventory");
            }

            inventory.setQuantity(
                    inventory.getQuantity()
                            - request.getQuantity());

            bloodInventoryRepository.save(inventory);
        }

        return bloodRequestRepository.save(request);
    }

    public void deleteBloodRequest(Long id) {
        bloodRequestRepository.deleteById(id);
    }
}