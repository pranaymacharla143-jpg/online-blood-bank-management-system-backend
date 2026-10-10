package blood_bank_management.controller;

import blood_bank_management.entity.Admin;
import blood_bank_management.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(
            @PathVariable Long id) {

        return adminService.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(
            @PathVariable Long id,
            @RequestBody Admin adminDetails) {

        return adminService.updateAdmin(id, adminDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(
            @PathVariable Long id) {

        adminService.deleteAdmin(id);

        return ResponseEntity.ok(
                "Admin deleted successfully");
    }
}