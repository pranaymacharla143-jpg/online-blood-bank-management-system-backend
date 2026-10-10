package blood_bank_management.controller;

import blood_bank_management.entity.Patient;
import blood_bank_management.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Add a new patient
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    // Get all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {

        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update patient
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patientDetails) {

        return patientService.updatePatient(id, patientDetails);
    }

    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {

        patientService.deletePatient(id);

        return ResponseEntity.ok("Patient deleted successfully");
    }
}