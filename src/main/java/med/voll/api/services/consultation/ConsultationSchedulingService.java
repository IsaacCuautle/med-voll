package med.voll.api.services.consultation;

import jakarta.validation.Valid;
import med.voll.api.dto.consultations.ConsultationDetails;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import med.voll.api.infra.exceptions.IntegrityValidation;
import med.voll.api.infra.validations.ConsultationValidation;
import med.voll.api.models.Consultation;
import med.voll.api.models.Doctor;
import med.voll.api.repositories.ConsultationRepository;
import med.voll.api.repositories.DoctorRepository;
import med.voll.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ConsultationSchedulingService
{

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    List<ConsultationValidation> validators;

    public ConsultationDetails scheduling(@RequestBody @Valid DataScheduleConsultation data)
    {
        // Comprobar el id de un paciente
        if (!patientRepository.findById(data.patientId()).isPresent())
        {
            throw new IntegrityValidation("Este paciente no fue encontrado");
        }

        // Comprueba el id del medico
        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId()))
        {
            throw new IntegrityValidation("Este medico no fue encontrado");
        }

        // Validaciones
        validators.forEach(v -> v.validate(data));

        // Asigna los valores para un objeto consulta y lo guarda en la DB
        var patient = patientRepository.findById(data.patientId()).get();
        var doctor = selectSimilarDoctor(data);

        if (doctor == null)
        {
            throw new IntegrityValidation("Sin medicos disponibles con esa especialidad y horario");
        }

        var consultation = new Consultation(null,doctor,patient,data.date());

        consultationRepository.save(consultation);

        return new ConsultationDetails(consultation);
    }

    // Selecciona un medico disponible con la misma especialidad
    private Doctor selectSimilarDoctor(DataScheduleConsultation data) {
        if (data.doctorId() != null)
        {
            return doctorRepository.getReferenceById(data.doctorId());
        }
        if(data.speciality() == null)
        {
            throw new IntegrityValidation("debe seleccionar una especialidad");
        }

        return doctorRepository.selectDoctorSimilarSpeciality(data.speciality(), data.date());
    }
}
