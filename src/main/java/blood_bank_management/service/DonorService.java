package blood_bank_management.service;

import blood_bank_management.entity.Donor;
import blood_bank_management.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public Donor addDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Optional<Donor> getDonorById(Long id) {
        return donorRepository.findById(id);
    }

    public Donor updateDonor(Long id, Donor donorDetails) {

        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        donor.setName(donorDetails.getName());
        donor.setEmail(donorDetails.getEmail());
        donor.setPhone(donorDetails.getPhone());
        donor.setBloodGroup(donorDetails.getBloodGroup());
        donor.setAge(donorDetails.getAge());
        donor.setGender(donorDetails.getGender());
        donor.setAddress(donorDetails.getAddress());

        return donorRepository.save(donor);
    }

    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }
}