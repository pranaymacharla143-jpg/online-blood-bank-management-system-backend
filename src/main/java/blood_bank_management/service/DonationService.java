package blood_bank_management.service;

import blood_bank_management.entity.BloodInventory;
import blood_bank_management.entity.Donation;
import blood_bank_management.repository.BloodInventoryRepository;
import blood_bank_management.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final BloodInventoryRepository bloodInventoryRepository;

    public DonationService(
            DonationRepository donationRepository,
            BloodInventoryRepository bloodInventoryRepository) {

        this.donationRepository = donationRepository;
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    public Donation addDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Optional<Donation> getDonationById(Long id) {
        return donationRepository.findById(id);
    }

    public Donation updateDonation(
            Long id,
            Donation donationDetails) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        String oldStatus = donation.getStatus();

        donation.setDonorId(donationDetails.getDonorId());
        donation.setBloodGroup(donationDetails.getBloodGroup());
        donation.setQuantity(donationDetails.getQuantity());
        donation.setDonationDate(donationDetails.getDonationDate());
        donation.setStatus(donationDetails.getStatus());

        Donation updatedDonation = donationRepository.save(donation);

        if (!"APPROVED".equalsIgnoreCase(oldStatus)
                && "APPROVED".equalsIgnoreCase(
                        donationDetails.getStatus())) {

            BloodInventory inventory = bloodInventoryRepository.findAll()
                    .stream()
                    .filter(i -> i.getBloodGroup()
                            .equalsIgnoreCase(
                                    donation.getBloodGroup()))
                    .findFirst()
                    .orElse(null);

            if (inventory == null) {

                inventory = new BloodInventory();
                inventory.setBloodGroup(
                        donation.getBloodGroup());
                inventory.setQuantity(
                        donation.getQuantity());

            } else {

                inventory.setQuantity(
                        inventory.getQuantity()
                                + donation.getQuantity());
            }

            bloodInventoryRepository.save(inventory);
        }

        return updatedDonation;
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}