package blood_bank_management.service;

import blood_bank_management.entity.Admin;
import blood_bank_management.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder) {

        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin addAdmin(Admin admin) {

        admin.setPassword(
                passwordEncoder.encode(admin.getPassword()));

        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setName(adminDetails.getName());
        admin.setEmail(adminDetails.getEmail());

        if (adminDetails.getPassword() != null
                && !adminDetails.getPassword().isEmpty()) {

            admin.setPassword(
                    passwordEncoder.encode(
                            adminDetails.getPassword()));
        }

        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}