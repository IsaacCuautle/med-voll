package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctor implements ConsultationValidation
{

    @Autowired
    private DoctorRepository repository;

    // Valida que sea un paciente activo
    public void validate(DataScheduleConsultation data)
    {
        if(data.doctorId() == null)
        {
            return;
        }

        var activeDoctor = repository.findActiveById(data.doctorId());

        if (!activeDoctor)
        {
            throw new ValidationException("Ese medico no esta activo");
        }
    }
}
