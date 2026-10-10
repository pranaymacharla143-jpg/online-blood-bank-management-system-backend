package blood_bank_management.repository;

import blood_bank_management.entity.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloodInventoryRepository
        extends JpaRepository<BloodInventory, Long> {

    Optional<BloodInventory> findByBloodGroupIgnoreCase(
            String bloodGroup);
}