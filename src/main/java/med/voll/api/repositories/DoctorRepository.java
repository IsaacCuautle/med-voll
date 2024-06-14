package med.voll.api.repositories;

import med.voll.api.dto.doctor.Speciality;
import med.voll.api.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>
{

    Page<Doctor> findByActiveTrue(Pageable pageable);

    // Trae un medico con una especilidad similar y disponible en una fecha especifica de forma aleatoria
    @Query("""
            SELECT d 
            FROM doctor d
            WHERE  d.active = true AND d.speciality=:speciality
            AND  d.id NOT IN(
                SELECT c.doctor.id FROM consultation c
                WHERE c.date=:date
            )
            ORDER BY RAND()
            LIMIT 1
            """)
     Doctor selectDoctorSimilarSpeciality(Speciality speciality, LocalDateTime date);

    @Query("""
            SELECT d.active
            FROM doctor d 
            WHERE d.id =:id 
            """)
    Boolean findActiveById(Long id);
}
