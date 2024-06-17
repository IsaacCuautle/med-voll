package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import med.voll.api.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutConsultation implements ConsultationValidation
{

    @Autowired
    private ConsultationRepository repository;

    // Valida que un pacinete solo realice una cita por dia
    public void validate(DataScheduleConsultation data)
    {
        var firtsSchedule = data.date().withHour(7);
        var lastSchedule = data.date().withHour(18);
        var patientWithConsultation = repository.existsByPatientIdAndDateBetween(data.patientId(), firtsSchedule, lastSchedule);

        if(patientWithConsultation)
        {
            throw new ValidationException("Ese paciente ya tiene una cita para este dia");
        }
    }
}
