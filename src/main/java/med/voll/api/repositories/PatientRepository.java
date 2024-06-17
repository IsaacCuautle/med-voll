package med.voll.api.repositories;

import med.voll.api.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PatientRepository extends JpaRepository<Patient, Long>
{

    Page<Patient> findByActiveTrue(Pageable pageable);

    @Query("""
            SELECT  p.active 
            FROM Patient p 
            WHERE p.id =:idPatient 
            """)
    Boolean findActiveById(Long idPatient);
}
