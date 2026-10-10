package blood_bank_management.service;

import blood_bank_management.entity.Admin;
import blood_bank_management.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder) {

        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Admin> login(String email, String password) {

        Optional<Admin> admin = adminRepository.findAll()
                .stream()
                .filter(a -> email != null
                        && password != null
                        && email.equals(a.getEmail())
                        && a.getPassword() != null
                        && passwordEncoder.matches(
                                password,
                                a.getPassword()))
                .findFirst();

        return admin;
    }
}