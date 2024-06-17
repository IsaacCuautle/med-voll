package med.voll.api.dto.consultations;

import med.voll.api.models.Consultation;
import java.time.LocalDateTime;

public record ConsultationDetails(
        Long patientId,
        Long doctorId,
        LocalDateTime date)
{


    public ConsultationDetails(Consultation consultation) {
        this(
                consultation.getPatient().getId(),
                consultation.getDoctor().getId(),
                consultation.getDate()
        );
    }
}
