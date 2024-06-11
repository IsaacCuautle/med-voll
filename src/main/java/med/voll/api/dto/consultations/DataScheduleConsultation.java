package med.voll.api.dto.consultations;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataScheduleConsultation( Long id,
                                        @NotNull Long patientId,
                                        Long doctorId,
                                        @NotNull LocalDateTime date)
{

}
