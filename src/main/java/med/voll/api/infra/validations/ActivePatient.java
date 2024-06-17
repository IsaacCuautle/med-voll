package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import med.voll.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatient implements ConsultationValidation
{

    @Autowired
    private PatientRepository repository;

    // Valida que sea un paciente activo
    public void validate(DataScheduleConsultation data)
    {
        if(data.patientId() == null)
        {
            return;
        }

        var activePatient = repository.findActiveById(data.patientId());

        if (!activePatient)
        {
            throw new ValidationException("Ese paciente no esta activo");
        }
    }
}
