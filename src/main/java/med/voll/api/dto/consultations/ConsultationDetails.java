package med.voll.api.dto.consultations;

import java.time.LocalDateTime;

public record ConsultationDetails(
        Long id,
        Long patientId,
        Long doctorId,
        LocalDateTime date)
{

}
