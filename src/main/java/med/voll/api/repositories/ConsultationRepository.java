package med.voll.api.repositories;

import med.voll.api.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long>
{

    Boolean existsByPatientIdAndDateBetween(Long id, LocalDateTime firtsSchedule, LocalDateTime lastSchedule);

    Boolean existsByDoctorIdAndDate(Long id, LocalDateTime date);
}
