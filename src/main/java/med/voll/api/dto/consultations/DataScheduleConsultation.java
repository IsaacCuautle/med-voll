package med.voll.api.dto.consultations;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.doctor.Speciality;

import java.time.LocalDateTime;

public record DataScheduleConsultation(
        Long id,
        @NotNull Long patientId,
        Long doctorId,
        Speciality speciality,
        @NotNull LocalDateTime date)
{

}
