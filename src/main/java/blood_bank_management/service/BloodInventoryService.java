package blood_bank_management.service;

import blood_bank_management.entity.BloodInventory;
import blood_bank_management.repository.BloodInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodInventoryService {

    private final BloodInventoryRepository bloodInventoryRepository;

    public BloodInventoryService(BloodInventoryRepository bloodInventoryRepository) {
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    public BloodInventory addBloodInventory(BloodInventory bloodInventory) {
        return bloodInventoryRepository.save(bloodInventory);
    }

    public List<BloodInventory> getAllBloodInventory() {
        return bloodInventoryRepository.findAll();
    }

    public Optional<BloodInventory> getBloodInventoryById(Long id) {
        return bloodInventoryRepository.findById(id);
    }

    public BloodInventory updateBloodInventory(
            Long id,
            BloodInventory inventoryDetails) {

        BloodInventory inventory = bloodInventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blood inventory not found"));

        inventory.setBloodGroup(inventoryDetails.getBloodGroup());
        inventory.setQuantity(inventoryDetails.getQuantity());

        return bloodInventoryRepository.save(inventory);
    }

    public void deleteBloodInventory(Long id) {
        bloodInventoryRepository.deleteById(id);
    }

    public Optional<BloodInventory> getBloodAvailability(
            String bloodGroup) {

        return bloodInventoryRepository
                .findByBloodGroupIgnoreCase(bloodGroup);
    }
}