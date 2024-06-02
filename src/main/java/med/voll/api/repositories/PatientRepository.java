package med.voll.api.repositories;

import med.voll.api.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>
{

}
