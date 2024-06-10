package med.voll.api.dto.consultations;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataScheduleConsultation( Long id, @NotNull Long idPatient, Long idMedic, @NotNull LocalDateTime date) {
}
